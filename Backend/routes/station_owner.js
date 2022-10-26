//IT19149318
//These are the rountes of the station owner

var express = require("express");
const {
  updateFuleStatus,
  addOwner,
} = require("../services/StationOwnerService");
var router = express.Router();

//upadte fule status route
router.put("/update-fuel-status", updateFuleStatus);

//register owner route
router.post("/register", addOwner);

module.exports = router;
