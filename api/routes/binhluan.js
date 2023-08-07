var express = require('express');
var router = express.Router();

var blCtrl = require('../COntrollers/binhluan.controller');
router.get('/', blCtrl.listBL);
router.get('/add-bl', blCtrl.addBL);
router.post('/add-bl', blCtrl.addBL);
router.put('/edit-bl/:id_bl', blCtrl.editBL);
router.delete('/delete-bl/:id_bl', blCtrl.deleteBL);
module.exports = router;
