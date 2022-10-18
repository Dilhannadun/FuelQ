const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
    email: {
        type: String,
        required: true,
        min: 4,
        max: 256
    },
})

module.exports = mongoose.model('User', userSchema);