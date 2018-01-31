const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const UserSchema = new Schema({
	name: {
		type: String,
		required: [true, "Name field is required"]
	},

	rank: {
		type: String,
	},
	
	available: {
		type: Boolean,
		default: false
	}
});

const User = mongoose.model("user", UserSchema);

module.exports = User;