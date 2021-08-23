
clickDivElem = function (element){
    document.location.href = "/post/" + element.id;
}

clickUser = function (user){
    document.location.href = "/account/" + user.id;
}

btnUpdate = function (user){
    document.location.href = "/update?id=" + user.id;
}


