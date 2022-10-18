const User = require('../models/User');

const addUser = async (req, res) => {
    console.log(req.body)
    const newUser = new User(req.body);
    
    newUser.save().then(data => {
        res.status(200).json({ user: data });
    }).catch(err => {
        res.status(400).json({ error: err });
    });
}

const getUser = async (req, res) => {
    const user = await User.findById(req.params.id);

    if (user) {
        res.status(200).json({ user: user });
    } else { 
        res.status(404).json({ error: 'Not Found' });
    }
}