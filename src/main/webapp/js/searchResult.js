const apiUrl = "http://localhost:8080/TPO6_BJ_S24322-1.0-SNAPSHOT/"

async function searchBooks() {
    const url = createApiRequestUrl();

    const response = await fetch(url);
    const jsonData = await response.json();

    createResultsOnPage(jsonData)
}


function createResultsOnPage(jsonData) {
    const searchResultContainer = document.getElementById("search-result-container");
    jsonData.forEach((item) => {
        const newDiv = document.createElement("div");
        newDiv.classList.add("search-result-item");

        const innerLeftDiv = document.createElement("div");
        innerLeftDiv.classList.add("item-left-size");

        const titleDiv = document.createElement("div");
        titleDiv.classList.add("item-title");
        titleDiv.textContent = item.title;

        const authorDiv = document.createElement("div");
        authorDiv.classList.add("item-author");
        authorDiv.textContent = item.author;

        const idDiv = document.createElement("div");
        idDiv.classList.add("item-id");
        idDiv.textContent = item.bookId;

        innerLeftDiv.appendChild(titleDiv);
        innerLeftDiv.appendChild(authorDiv);
        innerLeftDiv.appendChild(idDiv);

        const innerRightDiv = document.createElement("div");
        innerRightDiv.classList.add("item-right-size");

        const descriptionDiv = document.createElement("div");
        descriptionDiv.classList.add("item-description");
        descriptionDiv.innerText = item.description;

        innerRightDiv.appendChild(descriptionDiv);

        newDiv.appendChild(innerLeftDiv);
        newDiv.appendChild(innerRightDiv);

        searchResultContainer.appendChild(newDiv);
    })
}

function createApiRequestUrl() {
    let url = window.location.href;
    url = url.replace("searchBooksResultPage", "search-books");
    return url;
}