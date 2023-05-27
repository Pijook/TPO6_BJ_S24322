class BookDto {
    constructor(title, author, description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }
}

const apiUrl = "http://localhost:8080/TPO6_BJ_S24322-1.0-SNAPSHOT/"

async function addBook() {
    const url = apiUrl + "add-book";
    const dto = createDto();

    const addStatusElement = document.getElementById("add-status");
    addStatusElement.innerText = "Status: Waiting";

    console.log(dto)

    const data = await fetch(url, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dto)
    });


    if(data.status === 201) {
        addStatusElement.innerText = "Status: Done";
    }
    else{
        addStatusElement.innerText = "Status: Error D:";
    }

    console.log(data);
}

function createDto() {
    return new BookDto(
        document.getElementById("title").value,
        document.getElementById("author").value,
        document.getElementById("description").value
    );
}