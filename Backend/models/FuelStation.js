// Model for FuelStation
// IT19069814

const mongoose = require('mongoose');

const fuelStationSchema = mongoose.Schema({
    stationName: {
        type: String,
        required: true
    },
    stationLocation: {
        type: String,
        required: true
    },
    stationLatitude: {
        type: Number,
        required: true
    },
    stationLongitude: {
        type: Number,
        required: true
    },
    petrol95Status: {
        type: Boolean,
        required: false  
    },
    petrol92Status: {
        type: Boolean,
        required: false  
    },
    dieselStatus: {
        type: Boolean,
        required: false  
    },
    queue: [{
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
    }]
})

module.exports = mongoose.model('FuelStation', fuelStationSchema);