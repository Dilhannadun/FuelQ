// Routes for FuelStation services
// IT19069814

var express = require('express');
const { addStation, 
    getNearbyStations, 
    getAll, 
    updateQueueByStationId } = require('../services/StationService')
var router = express.Router();

router.get('/', getAll);
router.post('/', addStation);
router.post('/getNearby', getNearbyStations);
router.patch('/updateQueue', updateQueueByStationId)


module.exports = router;
