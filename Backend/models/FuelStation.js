const mongoose = require("mongoose");

const fuelStationSchema = mongoose.Schema({
  stationId: {
    type: String,
    required: true,
  },
  stationName: {
    type: String,
    required: true,
  },
  stationLocation: {
    type: String,
    required: true,
  },
  stationLatitude: {
    type: Number,
    required: true,
  },
  stationLongitude: {
    type: Number,
    required: true,
  },
  petrol95Status: {
    type: Boolean,
    required: false,
  },
  petrol92Status: {
    type: Boolean,
    required: false,
  },
  dieselStatus: {
    type: Boolean,
    required: false,
  },
  queue: [
    {
      type: mongoose.Types.ObjectId,
      ref: "Queue",
      required: false,
    },
  ],
});

module.exports = mongoose.model("FuelStation", fuelStationSchema);
