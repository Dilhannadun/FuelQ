const mongoose = require("mongoose");

const StationOwnerSchema = new mongoose.Schema({
  fuleType: {
    type: String,
    required: true,
    min: 4,
    max: 100,
  },
  arrivalTime: {
    type: String,
    required: true,
    min: 6,
    max: 100,
  },
  finshedTime: {
    type: String,
    required: true,
    min: 6,
    max: 100,
  },
  status: {
    type: String,
    required: true,
    min: 6,
    max: 100,
  },
});

module.exports = mongoose.model("StationOwner", StationOwnerSchema);
