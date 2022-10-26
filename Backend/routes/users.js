// Routes for User services
// IT19069814

var express = require('express');
const { getUser, addUser } = require('../services/UserService');
var router = express.Router();

router.get('/:id', getUser);
router.post('/', addUser);

module.exports = router;
