const myMd = require('../Model/binhluan.model');
exports.listBL = async (req, res,next)=>{
    let dk_loc = null;
    if (typeof(req.query.id_truyen)!= 'undefined') {
        dk_loc = {id_truyen: req.query.id_truyen}
    }
    var list = await myMd.binhluanModel.find(dk_loc).populate('id_truyen','tentruyen');
    res.send(list);
}
exports.addBL = async(req, res, next)=>{
    if (req.method=='POST') {
        let objBL = new myMd.binhluanModel();
        objBL.id_truyen = req.body.id_truyen;
        objBL.id_user = req.body.id_user;
        objBL.noidung = req.body.noidung;
        objBL.thoigian = req.body.thoigian;
        try {
            let new_bl = await objBL.save();
            console.log(new_bl);
        } catch (error) {
            
        }
        res.send(objBL);
    }
}
exports.editBL = async (req, res,next)=>{
    if (req.method=='PUT') {
        let id_bl = req.params.id_bl;
        let objBL = new myMd.binhluanModel();
        objBL.id_truyen = req.body.id_truyen;
        objBL.id_user = req.body.id_user;
        objBL.noidung = req.body.noidung;
        objBL.thoigian = req.body.thoigian;
        objBL._id = req.body._id;
        try {
        await myMd.binhluanModel.findByIdAndUpdate({_id:id_bl}, objBL);
        } catch (error) {
            
        }
        res.send(objBL);
    }
}
exports.deleteBL = async (req, res, next)=>{
    if (req.method=='DELETE') {
        let id_bl = req.params.id_bl;
        try {
            await myMd.binhluanModel.findByIdAndDelete({_id: id_bl});
        } catch (error) {
            
        }
        res.send("Xóa thành công")    }

}