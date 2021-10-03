#!/usr/bin/env node
//used to tell about the environment to the os
let fs = require("fs");
let inputArr = process.argv.slice(2);//used to take input from usser
let path = require("path");
let helpObj= require("./commands/help");
let treeObj= require("./commands/tree");
let organizeObj= require("./commands/organize");
// let typeObj = require("./commands/utility");
// const { ChildProcess } = require('child_process');
// const { dirname } = require('path/posix');

// console.log(inputArr);
//node main.js tree "directoryPath"
//node main.js organize "directoryPath"
//node main.js help

let command = inputArr[0];
switch (command) {
    case "tree":
        treeObj.treeKey(inputArr[1]);
        break;
    case "organize":
        organizeObj.organizeKey(inputArr[1]);
        break;
    case "help":
        helpObj.helpKey();
        break;
    default:
        console.log("Please Input Right Command");
        break;
}

