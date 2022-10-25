const Station = require('../models/FuelStation');

// {"latitude":37.42342342342342,"longitude":-122.08395287867832}

const addStation = async (req, res) => {
    const newStation = new Station(req.body);
    console.log(newStation);

    newStation.save().then(data => {
        res.status(200).json({ station: data });
    }).catch(err => {
        res.status(400).json({ error: err });
    });
}

const getNearbyStations = async (req, res) => {
    const userLatitude = req.body.latitude;
    const userLongitude = req.body.longitude;
    const nearbySheds = [];
    console.log(userLatitude, userLongitude)

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
        console.log(nearbySheds)
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

module.exports = { getNearbyStations, addStation, getAll }