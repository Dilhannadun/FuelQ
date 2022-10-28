//IT19149318
//These are the services of the station owner

const Owner = require("../models/StationOwner");
const Station = require("../models/FuelStation");

//get Station by id
const getOwnerStationByID = async (req, res) => {
  let ownerStation;
  console.log(req.params.id);
  Station.findOne(
    {
      stationId: req.params.id,
    },
    function (err, station) {
      ownerStation = station;
      console.log(ownerStation);
      res.status(200).json(ownerStation);
    }
  );
};

//owner registration service
const addOwner = async (req, res) => {
  console.log(req.body);
  const newOwner = new Owner(req.body);
  newOwner
    .save()
    .then((data) => {
      res.status(200).json({ owner: data });
      console.log(data);
    })
    .catch((err) => {
      res.status(400).json({ error: err });
    });
};

//service for the fuel status update
const updateFuleStatus = async (req, res) => {
  let status = await Station.updateOne(
    { stationId: req.params.id },
    {
      $set: {
        petrol95Status: req.body.petrol95,
        petrol92Status: req.body.petrol92,
        dieselStatus: req.body.diesel,
      },
    }
  );
  res.status(200).json(status);
};

module.exports = {
  getOwnerStationByID,
  addOwner,
  updateFuleStatus,
};
