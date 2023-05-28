const apiUrl = "http://localhost:8080/TPO6_BJ_S24322-1.0-SNAPSHOT/"

async function searchBooks() {
    window.location.href = createNewUrl();
}

function changeMoreCriteriaAppearence() {
    const formElement = document.getElementById("more-search-criteria");
    const textDisplayElement = document.getElementById("more-search-fields-text");

    if(formElement.style.display === "none") {
        formElement.style.display = "block";
        textDisplayElement.innerHTML = "Mniej pól";
    }
    else {
        formElement.style.display = "none";
        textDisplayElement.innerHTML = "Więcej pól";
    }
}

function createNewUrl() {
    const form = document.querySelectorAll('#author, #title, #isbn, #rating');

    let url = apiUrl + "searchBooksResultPage";

    let empty = true;
    for(let i = 0; i < form.length; i++){
        if(form[i].value == null || form[i].value === '') {
            continue;
        }

        if(empty) {
            url = url + "?";
            empty = false;
        }
        else{
            url = url + "&";
        }

        url = url + form[i].name + "=" + form[i].value;
    }

    return url;
}