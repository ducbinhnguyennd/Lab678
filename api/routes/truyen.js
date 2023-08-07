var express = require('express');
var router = express.Router();

var TrCtrl = require('../COntrollers/truyen.controller');
router.get('/', TrCtrl.listTruyen);
router.get('/add-tr', TrCtrl.addTruyen);
router.post('/add-tr', TrCtrl.addTruyen);
router.put('/edit-tr/:id_tr', TrCtrl.edittruyen);
router.delete('/delete-tr/:id_tr', TrCtrl.deletetruyen);
module.exports = router;
