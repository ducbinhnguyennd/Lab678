const myMd = require('../Model/user.model');
exports.listUser = async (req, res, next) => {
    //  res.render('product/list', {sp: objDSSP});
    // var list = await myMd.userModel.find();
    let dk_loc = null;
    if (typeof (req.query.username) != 'undefined') {
        dk_loc = { username: req.query.username }
    }
    var list = await myMd.userModel.find(dk_loc).populate('username');
    res.send(list);
}
exports.addUser = async (req, res, next) => {

    if (req.method == 'POST') {
        let objUs = new myMd.userModel();
        objUs.username = req.body.username;
        objUs.passwd = req.body.passwd;
        objUs.email = req.body.email;
        objUs.fullname = req.body.fullname;
        try {
            let new_us = await objUs.save();
            console.log(new_us);
        } catch (error) {
        }
        res.send(objUs);
    }
}
// exports.editUser = async (req, res, next) => {
//     if (req.method == 'PUT') {
//         let iduser = req.params.iduser;
//         let objUs = new myMd.userModel();
//         objUs.username = req.body.username;
//         objUs.passwd = req.body.passwd;
//         objUs.email = req.body.email;
//         objUs.fullname = req.body.fullname;
//         objUs._id = req.body._id;
//         try {
//             await myMd.userModel.findByIdAndUpdate({ _id: iduser }, objUs);
//         } catch (error) {
//         }
//         res.send(objUs);
//     }
// }
exports.editUser = async (req, res, next) => {
    if (req.method === 'PUT') {
        let iduser = req.params.iduser;
        let objUs = {
            username: req.body.username,
            passwd: req.body.passwd,
            email: req.body.email,
            fullname: req.body.fullname,
            _id: req.body._id
        };

        try {
            await myMd.userModel.findByIdAndUpdate({ _id: iduser }, objUs);
            res.send(objUs);
        } catch (error) {
            console.error(error);
            res.status(500).send("Có lỗi xảy ra khi cập nhật người dùng.");
        }
    }
};
exports.deleteUser = async (req, res, next) => {
    if (req.method == 'DELETE') {
        let iduser = req.params.iduser;
        try {
            await myMd.userModel.findByIdAndDelete({ _id: iduser });
        } catch (error) {
        }
        res.send("Xóa thành công");
    }
}