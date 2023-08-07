var express = require('express');
var router = express.Router();

var UserCtrl = require('../COntrollers/user.controller');
router.get('/', UserCtrl.listUser);
router.get('/add-user', UserCtrl.addUser);
router.post('/add-user', UserCtrl.addUser);
router.put('/edit-user/:iduser', UserCtrl.editUser);
router.delete('/delete-user/:iduser', UserCtrl.deleteUser);
module.exports = router;
