
let url = "http://localhost:8080/advertisement/getAdvertisementUser"

function get (url){
    return fetch(url).then((response) => {
        return response.json();
      })
    }
get(url).then(data => addToPage(data));


function addToPage(advertisement){

    advertisement.forEach(ads => {
    let div = document.querySelector(".content_right");

    let element = document.createElement("div");
        element.classList.add('element');
        element.id = ads.id;

    let img = document.createElement('img');
        img.id = 'img';
        img.src = ads.img;
        img.classList.add("img");

    let name = document.createElement('p');
        name.classList.add('name');
        name.innerText = ads.name;

    let price = document.createElement('p');
        price.classList.add('price');
        price.innerText = ads.price + "грн";
        
    let location = document.createElement('p');
        location.classList.add('location');
        location.innerText = ads.location;
    
    let dataPublication = document.createElement('p');
        dataPublication.classList.add('dataPublication');
        dataPublication.innerText = ads.data;

    let deletePost = document.createElement('button');
        deletePost.classList.add('btn');
        deletePost.innerText = "Delete";
        deletePost.id = "deleteBtn";

    let addPost = document.createElement('button');
        addPost.classList.add('btn');
        addPost.innerText = "Update";
        addPost.style.marginLeft = "5px";
        addPost.id = "updateBtn";

        element.append(img, name, price, location, dataPublication, addPost, deletePost);
    div.append(element);

    element.onclick = function(){
        // const parent = document.querySelector('.content_right');
        //
        // while (parent.firstChild) {
        //     parent.firstChild.remove();
        // }
        // addToPageInfo(element.id);
    }
    });
}
