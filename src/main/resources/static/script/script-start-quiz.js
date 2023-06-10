function startQuiz(){
    let name = document.getElementById("name").value;

    // Check if the name is not empty
    if(name !== ""){
        let url = window.location.href;

        // Remove trailing slash from the URL, if present
        if (url[url.length - 1] === "/"){
            url = url.slice(0, -1);
        }

        // Open a new URL with the name appended and navigate to it
        window.open(url + "/" + name + "/0/0", "_self");
    }
}
