// Model for Queue
// IT19069814

const mongoose = require('mongoose');

const fuelQueueSchema = mongoose.Schema({
    fuelType: {
        type: String,
        required: false
    },
    numberOfVehicles: {
        type: Number,
        required: false
    },
    numberOfVehiclesOut: {
        type: Number,
        required: false
    },
    totalTime: {
        type: Number,
        required: false
    }
})

module.exports = mongoose.model('Queue', fuelQueueSchema);