let fs= require("fs");
let utility = {}
utility.types = {
    media: ["mp4", 'mkv', 'mp3', 'mp2'],
    archives: ['zip', '7z', 'rar', 'tar', 'gz', 'ar', 'iso', 'xz'],
    documents: ['docs', 'docx', 'doc', 'pdf', 'xlsx', 'xls', 'odt', 'ods', 'odp', 'odg', 'odf', 'txt', 'ps', 'tex'],
    app: ['exe', 'dmg', 'pkg', "deb"],
    program: ['java', 'c', 'cpp', 'js', 'html', 'css'],
    picture: ['png', 'jpeg', 'jpg']
}
module.exports= utility;