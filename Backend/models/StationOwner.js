//IT19149318
//This is the mongoose schema for the station owner

const mongoose = require("mongoose");

const StationOwnerSchema = new mongoose.Schema({
  nic: {
    type: String,
    required: true,
    min: 10,
    max: 12,
  },
  station_id: {
    type: String,
    required: true,
    min: 6,
    max: 6,
  },
  phone: {
    type: String,
    required: true,
    min: 4,
    max: 256,
  },
  email: {
    type: String,
    required: true,
    min: 4,
    max: 256,
  },
  station: {
    type: mongoose.Types.ObjectId,
    ref: "FuelStation",
    required: false,
  },
});

module.exports = mongoose.model("StationOwner", StationOwnerSchema);
