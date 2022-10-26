// Fuel Station Services class
// IT19069814

const FuelStation = require('../models/FuelStation');
const Station = require('../models/FuelStation');

// Method responsible for adding stations
const addStation = async (req, res) => {
    const newStation = new Station(req.body);

    newStation.save().then(data => {
        res.status(200).json({ station: data });
    }).catch(err => {
        res.status(400).json({ error: err });
    });
}

// Method responsible for retreiving stations near
// the user
const getNearbyStations = async (req, res) => {
    const userLatitude = req.body.latitude;
    const userLongitude = req.body.longitude;
    const nearbySheds = [];

    const allSheds = await getAllStations();
    if (allSheds.length > 0) {
        allSheds.forEach(shed => {
            const distance =
                getDistanceBetweenCoordinates(
                    userLatitude,
                    userLongitude,
                    shed.stationLatitude,
                    shed.stationLongitude
                )
            if (distance < 8) nearbySheds.push(shed)
        });
    }
    res.status(200).json({ stations: nearbySheds })
}

const getAllStations = async () => {
    return await Station.find({});
}

const getAll = async (req, res) => {
    const stations = await Station.find({});
    res.status(200).json({ stations });
}

// Updating the status of the queue
// Called when a vehicle joins or leaves the queue
const updateQueueByStationId = async (req, res) => {
    const station = await FuelStation.findById(req.body.id);
    console.log(station._id)
    const { fuelType, isJoining, time } = req.body;
    const queue = station.queue.map(q => q.fuelType == fuelType)[0];
    console.log(req.body)
    if (isJoining) {
        if (queue == undefined || !queue) {
            console.log("queue null")
            const newQueue = {
                fuelType,
                numberOfVehicles: 1,
                numberOfVehiclesOut: 0,
                totalTime: 0
            }
            Station.updateOne({ "_id": station._id },
                { '$push': { 'queue': newQueue } })
                .then(() => res.status(200).json(station))
        } else {
            console.log("queue not null")
            Station.updateOne(
                { '_id': station._id, 'queue.fuelType': fuelType },
                { 
                    '$inc': { 'queue.$.numberOfVehicles': 1 }
                })
                .then(() => res.status(200).json(station))
        }

    } else {
        Station.updateOne(
            { '_id': station._id, 'queue.fuelType': fuelType },
            {
                '$inc': { 
                    'queue.$.numberOfVehicles': -1,
                    'queue.$.numberOfVehiclesOut': 1 },
                '$set': { 'queue.$.totalTime': time },
            })
            .then(() => res.status(200).json(station))
    }
}

// function to calculate distance between user location
// and stations
// src: https://stackoverflow.com/questions/18883601/function-to-calculate-distance-between-two-coordinates
const getDistanceBetweenCoordinates = (userLat, userLon, shedLat, shedLon) => {

    var R = 6371; // Radius of the earth in km
    var dLat = deg2rad(shedLat - userLat);
    var dLon = deg2rad(shedLon - userLon);
    var a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(userLat)) * Math.cos(deg2rad(shedLat)) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2)
        ;
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    var d = R * c; // Distance in km
    return d;
}

const deg2rad = (deg) => {
    return deg * (Math.PI / 180)
}

module.exports = { getNearbyStations, addStation, getAll, updateQueueByStationId }