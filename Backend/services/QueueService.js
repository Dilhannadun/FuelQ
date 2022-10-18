const Queue = require('../models/Queue');
const FuelStation = require('../models/FuelStation');

const getQueueLengthOfStation = async (req, res) => {
    const fuelStation = await FuelStation.findById(req.params.id);
    const fuelType = req.body.fuelType;

    const vehicles = fuelStation
        .queues
        .map((queue) => queue.fuelType == fuelType)
        .numberOfVehicles;

    res.status(200).json({ length: vehicles });

}

const getAverageTimeInQueue = async (req, res) => {
    const fuelStation = await FuelStation.findById(req.params.id);
    const fuelType = req.body.fuelType;

    const queue = fuelStation
        .queues
        .map((queue) => queue.fuelType == fuelType);

    const waitTime = res.totalTime / res.numberOfVehiclesOut;
    
    res.status(200).json({ time: waitTime })
}