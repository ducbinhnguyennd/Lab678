const myMd = require('../Model/truyen.model');



exports.listTruyen = async (req,res,next)=>{
    let dk_loc = null;
    if (typeof(req.query.tentruyen)!= 'undefined') {
        dk_loc = {tentruyen: req.query.tentruyen}
    }
    var list = await myMd.truyenModel.find(dk_loc).populate('tentruyen');
    res.send(list);
}
exports.addTruyen = async (req,res,next)=>{
    if (req.method=='POST') {
        let objTR = new myMd.truyenModel();
        objTR.tentruyen = req.body.tentruyen;
        objTR.mota = req.body.mota;
        objTR.tentacgia = req.body.tentacgia;
        objTR.namxb = req.body.namxb;
        objTR.anhbia = req.body.anhbia;
        objTR.listanh = req.body.listanh;
        try {
            let new_tr = await objTR.save();
            console.log(new_tr);
        } catch (error) {
            
        }
        res.send(objTR);
    }
}
exports.edittruyen = async (req, res,next)=>{
    if (req.method=='PUT') {
        let id_tr = req.params.id_tr;
        let objTR = new myMd.truyenModel();
        objTR.tentruyen = req.body.tentruyen;
        objTR.mota = req.body.mota;
        objTR.tentacgia = req.body.tentacgia;
        objTR.namxb = req.body.namxb;
        objTR.anhbia = req.body.anhbia;
        objTR.listanh = req.body.listanh;
        objTR._id = req.body._id;

        try {
          await myMd.truyenModel.findByIdAndUpdate({_id: id_tr}, objTR);
        } catch (error) {
            
        }
        res.send(objTR);
    }
}
exports.deletetruyen = async (req, res, next)=>{
    if (req.method=='DELETE') {
        let id_tr = req.params.id_tr;
        try {
            await myMd.truyenModel.findByIdAndDelete({_id:id_tr});
        } catch (error) {
            
        }
        res.send("Xóa thành công");
    }
}