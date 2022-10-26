const Owner = require("../models/StationOwner");
const Station = require("../models/FuelStation");

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

const updateFuleStatus = async (req, res) => {
  const station = await Station.findById(req.body.sataion);
  station.petrol95Status = req.body.petrol95;
  station.petrol92Status = req.body.petrol92;
  station.dieselStatus = req.body.diesel;

  station
    .save()
    .then((data) => {
      res.status(200).json({ owner: data });
      console.log(data);
    })
    .catch((err) => {
      res.status(400).json({ error: err });
    });
};

module.exports = {
  addOwner,
  updateFuleStatus,
};
