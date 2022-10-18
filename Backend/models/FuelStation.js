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
        totalAmount: {
            type: Number,
            required: true
        },
        remaining: {
            type: Number,  
            required: true
        }
    },
    petrol92Status: {
        totalAmount: {
            type: Number,
            required: true
        },
        remaining: {
            type: Number,  
            required: true
        }
    },
    dieselStatus: {
        totalAmount: {
            type: Number,
            required: true
        },
        remaining: {
            type: Number,  
            required: true
        }
    },
    queue: [{
        type: mongoose.Types.ObjectId,
        ref: 'Queue',
        required: false
    }]
})

module.exports = mongoose.model('FuelStation', fuelStationSchema);