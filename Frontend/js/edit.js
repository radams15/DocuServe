function includeJs(jsFilePath) {
    var js = document.createElement("script");

    js.type = "text/javascript";
    js.src = jsFilePath;

    document.body.appendChild(js);
}

includeJs("js/constants.js.php");

function saveDoc(path){
    var url = baseUrl+"/document";

    console.log(url);

    var xhr = new XMLHttpRequest();

    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        content: document.getElementById("mdArea").value,
        path: path
    }));
}

function renderDoc(path){
    saveDoc(path);
    location.reload();
}