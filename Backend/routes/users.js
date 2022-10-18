var express = require('express');
const { getUser } = require('../services/UserService');
var router = express.Router();

/* GET users listing. */
router.get('/:id', getUser);

module.exports = router;
