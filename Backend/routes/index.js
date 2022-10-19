var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  const user = { email: "chandu" }
  res.status(200).json({user : user});
});

module.exports = router;
