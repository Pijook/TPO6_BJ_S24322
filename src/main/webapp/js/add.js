class BookDto {
    constructor(title, author, description, isbn, publishDate, rating) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.rating = rating;
    }
}

const apiUrl = "http://localhost:8080/TPO6_BJ_S24322-1.0-SNAPSHOT/"

async function addBook() {
    const url = apiUrl + "api/add-book";
    const dto = createDto();

    const addStatusElement = document.getElementById("add-result-text");
    addStatusElement.innerText = "Status: Waiting";


    const data = await fetch(url, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dto)
    });


    if(data.status === 201) {
        addStatusElement.innerText = "Status: Dodano!";
    }
    else{
        addStatusElement.innerText = "Status: Wystąpił błąd";
    }

}

function createDto() {
    return new BookDto(
        document.getElementById("title").value,
        document.getElementById("author").value,
        document.getElementById("description").value,
        document.getElementById("isbn").value,
        document.getElementById("publishDate").value,
        document.getElementById("rating").value
    );
}