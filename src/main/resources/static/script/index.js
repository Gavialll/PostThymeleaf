
let url  = "http://localhost:8080/advertisement/getAll";

user1 = { 
    id: 3,  
    firstName: "newPeopleRomik",
    lastName: "lastPeople",
    email: "email",
    password: "pasword",
    login: "login"
};

advertisement = {
    name: "new car",
    price: 1000.0,
    description: "description car",
    img: "car Foto",
    category: "new category",
    user: user1
};

comment = {
    message: "гарна машина",
    advertisementId: 1,
    user: user1
}

/* async function postData(url = '', data = {}) {
//     const response = await fetch(
//   url, {
//       method: 'POST', 
//       mode: 'cors', 
//       cache: 'no-cache',
//       credentials: 'same-origin', 
  
//       headers: {
//         'Content-Type': 'application/json'
//       },
//       redirect: 'follow',
//       referrerPolicy: 'no-referrer', 
//       body: JSON.stringify(data)
//     });
//   }
//   postData(url4, comment);*/

///////////////////////////////////////////////////////////////////////////////////////////////////////////
// Print AllAdvertisement
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
        location.innerText = "Львів"; 
    
    let dataPublication = document.createElement('p');
        dataPublication.classList.add('dataPublication');
        dataPublication.innerText = "28 червня, 11:35"; 

        element.append(img, name, price, location, dataPublication);
    div.append(element);

    element.onclick = function(){
        const parent = document.querySelector('.content_right');
    
        while (parent.firstChild) {
            parent.firstChild.remove();
        }
        addToPageInfo(element.id);
    }
    });
}
//fetch get запит
function get (url){
    return fetch(url).then((response) => {
        return response.json();
      })
    }
get(url).then(data => addToPage(data));
///////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////
// Print Advertisement 
async function addToPageInfo(id){

        let url = "/advertisement/getId?id=" + id;

        //get server
        let response = await fetch(url);
        let result = await response.json();
            
        let ads = await result;

        console.log(ads);

        let content_right = document.querySelector(".content_right")

        let img = document.createElement('img');
            img.src = ads.img;
            img.classList.add("img_info");

           
        let h2 = document.createElement('h2');
            h2.classList.add("name_product");
            h2.innerText = ads.name;

        let p_price = document.createElement('p');
            p_price.classList.add('price_product');
            p_price.innerText = ads.price + "грн";

        let h5 = document.createElement('h5');
            h5.classList.add("description");
            h5.innerText = "Опис";

        let p_description = document.createElement('p');
            p_description.classList.add('distinct_product')  
            p_description.innerText = ads.description ;

        let div1 = document.createElement('div');
            div1.classList.add('info');

        let div = document.createElement('div');
            div.classList.add("advertisement_print")

                div1.append(h2, p_price, h5, p_description);
            div.append(img, div1);  
        content_right.append(div);

        addUserInfo(ads.user);

        addToPageComment(ads.id);
        
}
async function addUserInfo(id){
    let url1 = "/user/getId?id=" + id;

    //get server
    let response = await fetch(url1);
    let result = await response.json();
        
    let user = await result;

    console.log(user);

    let advertisement_print = document.querySelector(".advertisement_print");
        
    let div1 = document.createElement('div');
        div1.style.display = "flex";
        div1.style.flexDirection = "column";

    let img = document.createElement('img');
        img.src = user.img;
        img.classList.add("img_user");

    let userName = document.createElement('p');
        userName.classList.add("info")
        userName.innerText = "Автор: " + user.firstName + " " + user.lastName;
        userName.id = user.id;

    let email = document.createElement('p');
        email.innerText = "Email: " +  user.email;

        let div = document.createElement('div');
            div.classList.add("userInfo")

            div1.append(userName, email);
        div.append(img, div1);
    advertisement_print.after(div);    
}

async function getUser(id){
    let url = "/user/getId?id=" + id;

    //get server
    let response = await fetch(url);
    let result = await response.json();
    
    let user = await result;

     console.log(user);

    return user;
}
async function addToPageComment(id){
    
    let url2 = "/comment/getAllByAdvertisement?id=" + id;

    //get server
    let response = await fetch(url2);
    let result = await response.json();
    let commentList = await result;

    console.log(commentList);

    commentList.forEach(element => {

        let userInfo = document.querySelector(".userInfo");

        let img = document.createElement('img');
            //img.src = user.img;
            img.classList.add("img_user");

        let h4 = document.createElement('h4');
            h4.style = "width: 500px;";
            h4.style = "border-bottom: 1px solid grey;";
            //h4.innerText = user1.firstName;

        let h5 = document.createElement('h5');
            h5.innerText = element.message;

        let div1 = document.createElement('div');
            div1.classList.add('info');

        let comment_column  = document.createElement("div");
            comment_column.classList.add("comment_column");  
                    
        let comment = document.createElement("div");
            comment.classList.add("comment");   

        let div = document.createElement('div');
            div.classList.add("allComment")    

                    comment_column.append(h4, h5);
                comment.append(img, comment_column);
            div.append(comment);  
        userInfo.after(div);
       }

    );
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////

let btn1 = document.getElementById("singin")
btn1.onclick = function(){
    document.location.href = "http://localhost:8080/login";
}


