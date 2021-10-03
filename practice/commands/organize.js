let fs = require("fs");
let path = require("path");
// let typeObj = require("./commands/types");
let types = {
    media: ["mp4", 'mkv', 'mp3', 'mp2'],
    archives: ['zip', '7z', 'rar', 'tar', 'gz', 'ar', 'iso', 'xz'],
    documents: ['docs', 'docx', 'doc', 'pdf', 'xlsx', 'xls', 'odt', 'ods', 'odp', 'odg', 'odf', 'txt', 'ps', 'tex'],
    app: ['exe', 'dmg', 'pkg', "deb"],
    program: ['java', 'c', 'cpp', 'js', 'html', 'css'],
    picture: ['png', 'jpeg', 'jpg']
}
function organizeHelper(src, destPath) {
    // (3)identifying the categories of all the files present in the input directory
    let childName = fs.readdirSync(src);//to read file name
    //The fs.readdirSync() method is used to synchronously read the contents of a given directory  The method returns an array with all the file names or objects in the directory.. 
    // console.log(childName);
    for (let i = 0; i < childName.length; i++) {
        //The path.join() method joins the specified path segments into one path.
        let childAddress = path.join(src, childName[i]);
        //lstatSync tells you both whether something exists, and if so, whether it's a file or a directory (or in some file systems, a symbolic link, block device, character device, etc.), e.g. if you need to know if it exists and is a directory:
        let isFile = fs.lstatSync(childAddress).isFile();
        if (isFile == true) {
            // console.log(childName[i]);

            let catagory = getCatagory(childName[i]);
            console.log(childName[i], "Belongs to -->", catagory);
            send_files_to_correct_dir(childAddress, destPath, catagory);
        }
    }
}
function getCatagory(name) {
    let ext = path.extname(name);
    // console.log(ext);
    //extname give extname with . sowe have to remove this .
    ext = ext.slice(1);
    for (let type in types) {
        let cTypeArray = types[type];
        for (let i = 0; i < cTypeArray.length; i++) {
            if (ext == cTypeArray[i]) {
                return type;
            }
        }
    }
    return "others";
}
function send_files_to_correct_dir(srcFilePath, destPath, catagory) {
    //creating a path till the directory
    let catagoryPath = path.join(destPath, catagory);
    //creating dedicated directory inside src->organized_files
    if (fs.existsSync(catagoryPath) == false) {
        fs.mkdirSync(catagoryPath);
    }
    let fileName = path.basename(srcFilePath);
    //The path.basename() method is used to get the filename portion of a path to the file. The trailing directory separators are ignored when using this method.
    let destFilePath = path.join(catagoryPath, fileName);
    fs.copyFileSync(srcFilePath, destFilePath);
    //The fs. copyFileSync() method is used to synchronously copy a file from the source path to destination path. Node. js will overwrite the file if it already exists in the destination
    fs.unlinkSync(srcFilePath);
    //The fs. unlinkSync() method is used to synchronously remove a file or symbolic link from the filesystem. 
    console.log(fileName, "Copied to", catagory);
}
function organizeFn(dirPath) {
    let destPath;
    // console.log("organize command is iplemented for ",dirPath);
    //directory path is given
    // (1) input-> directory path given
    if (dirPath == undefined) {
        destPath = process.cwd();
        return;
    } else {
        let doesExist = fs.existsSync(dirPath);
        //The fs.existsSync() method is used to synchronously check if a file already exists in the given path or not. It returns a boolean value which indicates the presence of a file.
        if (doesExist) {
            // (2) create-> organize_files -> directory 
            destPath = path.join(dirPath, "organized_files");
            if (fs.existsSync(destPath) == false) {
                //The fs.existsSync() method is used to synchronously check if a file already exists in the given path or not. It returns a boolean value which indicates the presence of a file.
                fs.mkdirSync(destPath);
            }
        } else {
            console.log("Enter the correct path");
            return;
        }
    }
    // (3)identifying the categories of all the files present in the input directory with the help of organizeHelper
    organizeHelper(dirPath, destPath);
    //(4)   copy/cut files to that organize directory inside of any folder in organizeHelper function

}
module.exports={
    organizeKey : organizeFn
}