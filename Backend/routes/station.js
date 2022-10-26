var express = require('express');
const { addStation, getNearbyStations, getAll } = require('../services/StationService')
var router = express.Router();

router.post('/', addStation);
router.post('/getNearby', getNearbyStations);
router.get('/', getAll);

module.exports = router;
