const db = require('./db');
const truyenSchema = new db.mongoose.Schema(
    {
        tentruyen:{type: String, required: true},
        mota: {type: String, required: true},
        tentacgia: {type: String, required: true},
        namxb: {type: Number, required: true},
        anhbia: {type: String, required: true},
        listanh: {type: String, required: true}
    },
    {
        collection: 'truyentranh'
    }
);
let truyenModel = db.mongoose.model('truyenModel', truyenSchema);
module.exports = {truyenModel};