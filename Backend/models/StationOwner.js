const mongoose = require("mongoose");

const StationOwnerSchema = new mongoose.Schema({
  nic: {
    type: String,
    required: true,
    min: 10,
    max: 12,
  },
  stationId: {
    type: String,
    required: true,
    min: 6,
    max: 6,
  },
  email: {
    type: String,
    required: true,
    min: 4,
    max: 256,
  },
});

module.exports = mongoose.model("StationOwner", StationOwnerSchema);
