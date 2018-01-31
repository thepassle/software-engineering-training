const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");

const routes = require('./routes/api');

const app = express();

mongoose.connect('mongodb://pschilp:test@ds119268.mlab.com:19268/ninjago');
mongoose.Promise = global.Promise;

app.use(bodyParser.json());

app.use('/api', routes);

app.use(function(err, req, res, next){
	res.status(422).send({error: err.message});
});

app.listen(process.env.port || 4000, function(){
	console.log("Server running on port: 4000");
});