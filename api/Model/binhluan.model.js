const db = require('./db');
const binhluanSchema = new db.mongoose.Schema(
    {
        id_truyen: {type:db.mongoose.Schema.Types.ObjectId, ref: 'truyenModel'},
        id_user: {type: db.mongoose.Schema.Types.ObjectId, ref: 'userModel'},
        noidung: {type: String, required: true},
        thoigian: {type: String, required: true}
    },
    {
        collection: 'binhluan'
    }
);
let binhluanModel = db.mongoose.model('binhluanModel', binhluanSchema);
module.exports = {binhluanModel};