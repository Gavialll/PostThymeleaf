let btnDeleteAccount = document.getElementById("deleteAccount")
    btnDeleteAccount.onclick = function () {
        if (confirm("Delete Account")) {
            document.location.href = "/account/delete";
        }
    }