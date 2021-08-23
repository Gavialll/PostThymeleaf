// let btn = document.getElementById("ul1")
//     btn.onclick = function(){
//
//     document.location.href = "/account/" + btn.getAttribute("value");
// }

let btn2 = document.getElementById("ul2")
    btn2.onclick = function(){
    document.location.href = "/";
}

clickAccount = function (element){
    document.location.href = "/account/" + element.id;
}

clickDivElem = function (element){
    document.location.href = "/post/" + element.id;
}

clickUser = function (user){
    document.location.href = "/account/" + user.id;
}

btnUpdate = function (user){
    document.location.href = "/update?id=" + user.id;
}


