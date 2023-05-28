const apiUrl = "http://localhost:8080/TPO6_BJ_S24322-1.0-SNAPSHOT/"

async function searchBooks() {
    const url = createApiRequestUrl();

    const response = await fetch(url);
    const jsonData = await response.json();

    createResultsOnPage(jsonData)
}

function changeApperanceOfMoreResults(id) {
    const formElement = document.getElementById("more-results-container-" + id);
    const mainItemElement = document.getElementById("search-result-item-" + id);
    const imgElement = document.getElementById("more-png");

    if(formElement.style.display === "none") {
        formElement.style.display = "block";
        mainItemElement.classList.add("more-results");
        imgElement.src = "img/less.png";
    }
    else {
        formElement.style.display = "none";
        mainItemElement.classList.remove("more-results");
        imgElement.src = "img/more.png";
    }
}

function createResultsOnPage(jsonData) {
    const searchResultContainer = document.getElementById("search-result-container");
    let i = 0;
    jsonData.forEach((item) => {
        const currentItemId = i;
        const newDiv = document.createElement("div");
        newDiv.classList.add("search-result-item");
        newDiv.id = "search-result-item-" + i;

        const innerLeftDiv = document.createElement("div");
        innerLeftDiv.classList.add("item-left-size");

        const titleDiv = document.createElement("div");
        titleDiv.classList.add("item-title");
        titleDiv.textContent = "Title: " + item.title;

        const authorDiv = document.createElement("div");
        authorDiv.classList.add("item-author");
        authorDiv.textContent = "Author: " + item.author;

        const idDiv = document.createElement("div");
        idDiv.classList.add("item-id");
        idDiv.textContent = "ID: " + item.bookId;

        innerLeftDiv.appendChild(titleDiv);
        innerLeftDiv.appendChild(authorDiv);
        innerLeftDiv.appendChild(idDiv);

        const moreResultsDiv = document.createElement("div");
        moreResultsDiv.classList.add("more-results-container");
        moreResultsDiv.id = "more-results-container-" + i;
        moreResultsDiv.style.display = "none";

        const isbn = document.createElement("div");
        isbn.classList.add("isbn");
        isbn.innerText = "ISBN: " + item.isbn;

        const publishDate = document.createElement("div");
        publishDate.classList.add("publishDate");
        publishDate.innerText = "Publish Date: " + item.publishDate;

        const creationDate = document.createElement("div");
        creationDate.classList.add("creationDate");
        creationDate.innerText = "Creation Date: " + item.creationDate;

        const rating = document.createElement("rating");
        rating.classList.add("rating");
        rating.innerText = "Rating: " + item.rating;

        moreResultsDiv.appendChild(isbn);
        moreResultsDiv.appendChild(creationDate);
        moreResultsDiv.appendChild(publishDate);
        moreResultsDiv.appendChild(rating);
        innerLeftDiv.appendChild(moreResultsDiv);

        const moreResultsButton = document.createElement("div");
        moreResultsButton.classList.add("more-results-button");
        moreResultsButton.onclick = function () {
            changeApperanceOfMoreResults(currentItemId)
        };

        const resultsImg = document.createElement("img");
        resultsImg.src = "img/more.png";
        resultsImg.id = "more-png";
        resultsImg.classList.add("more-png");

        moreResultsButton.appendChild(resultsImg);
        innerLeftDiv.appendChild(moreResultsButton);

        const innerRightDiv = document.createElement("div");
        innerRightDiv.classList.add("item-right-size");

        const descriptionDiv = document.createElement("div");
        descriptionDiv.classList.add("item-description");
        descriptionDiv.innerText = item.description;

        innerRightDiv.appendChild(descriptionDiv);

        newDiv.appendChild(innerLeftDiv);
        newDiv.appendChild(innerRightDiv);

        searchResultContainer.appendChild(newDiv);

        i++;
    })
}

function createApiRequestUrl() {
    let url = window.location.href;
    url = url.replace("searchBooksResultPage", "api/search-books");
    return url;
}