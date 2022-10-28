//IT19149318
//These are the rountes of the station owner

var express = require("express");
const {
  getOwnerStationByID,
  updateFuleStatus,
  addOwner,
} = require("../services/StationOwnerService");
var router = express.Router();

//get shed by id
router.get("/:id", getOwnerStationByID);

//upadte fule status route
router.patch("/update-fuel-status/:id", updateFuleStatus);

//register owner route
router.post("/register", addOwner);

module.exports = router;
