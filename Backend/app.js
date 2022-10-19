var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var mongoose = require("mongoose");

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var app = express();


app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

app.use('/api', indexRouter);
app.use('/api/users', usersRouter);

mongoose.connect(
  process.env.DATABASE_CREDENTIALS,
  {
    useNewUrlParser: true,
    useUnifiedTopology: true,
    useFindAndModify: false,
    useCreateIndex: true,
  },
  () => {
    console.log("Database connected");
  }
);

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});

