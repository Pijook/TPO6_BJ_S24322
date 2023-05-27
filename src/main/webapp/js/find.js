const apiUrl = "http://localhost:8080/TPO6_BJ_S24322-1.0-SNAPSHOT/"

async function searchBooks() {
/*    const url = createUrl();

    const response = await fetch(url);
    const jsonData = await response.json();*/

   /* window.location.href = */createNewUrl();
}

function mapDataToTable(jsonData) {
    const container = document.getElementById("tableContainer");
    container.innerText = ""

    let table = document.createElement("table");

    let cols = Object.keys(jsonData[0]);

    let thead = document.createElement("thead");
    let tr = document.createElement("tr");

    cols.forEach((item) => {
        item = item.replace(/([A-Z])/g, ' $1').trim()
        item = item.charAt(0).toUpperCase() + item.slice(1);
        let th = document.createElement("th");
        th.innerText = item;
        tr.appendChild(th);
    });
    thead.appendChild(tr);
    table.append(tr);

    jsonData.forEach((item) => {
        let tr = document.createElement("tr");
        let vals = Object.values(item);

        vals.forEach((elem) => {
            let td = document.createElement("td");
            td.innerText = elem;
            tr.appendChild(td);
        });
        table.appendChild(tr);
    });

    container.appendChild(table);
}

function createUrl() {
    const form = document.querySelectorAll('#bookId');

    let url = apiUrl + "search-books";

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

function createNewUrl() {
    const form = document.querySelectorAll('#bookId');

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
    console.log(url)
    return url;
}