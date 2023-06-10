function sortQuizzes() {
    // Retrieve the sorting value from the select element
    let selectSort = document.getElementById("select-sort");
    let sortValue = selectSort.value;

    // Get the container element for quizzes and retrieve all quiz elements
    let quizSection = document.querySelector(".my-quizzes");
    let quizzes = quizSection.querySelectorAll(".quiz");

    // Convert the NodeList to an array to enable sorting
    let sortedQuizzes = Array.from(quizzes);

    // Sort the quizzes based on the selected sorting value
    if (sortValue === "ascending") {
        // Sort in ascending order based on the quiz title
        sortedQuizzes.sort(function (a, b) {
            let titleA = a.querySelector(".left-info p:first-child").textContent.toUpperCase();
            let titleB = b.querySelector(".left-info p:first-child").textContent.toUpperCase();
            return titleA.localeCompare(titleB);
        });
    } else if (sortValue === "descending") {
        // Sort in descending order based on the quiz title
        sortedQuizzes.sort(function (a, b) {
            let titleA = a.querySelector(".left-info p:first-child").textContent.toUpperCase();
            let titleB = b.querySelector(".left-info p:first-child").textContent.toUpperCase();
            return titleB.localeCompare(titleA);
        });
    } else if (sortValue === "max-likes") {
        // Sort in descending order based on the number of likes
        sortedQuizzes.sort(function (a, b) {
            let likesA = parseInt(a.querySelector(".right-info p:last-child b").textContent);
            let likesB = parseInt(b.querySelector(".right-info p:last-child b").textContent);
            return likesB - likesA;
        });
    } else if (sortValue === "min-likes") {
        // Sort in ascending order based on the number of likes
        sortedQuizzes.sort(function (a, b) {
            let likesA = parseInt(a.querySelector(".right-info p:last-child b").textContent);
            let likesB = parseInt(b.querySelector(".right-info p:last-child b").textContent);
            return likesA - likesB;
        });
    } else if (sortValue === "max-questions") {
        // Sort in descending order based on the number of questions
        sortedQuizzes.sort(function (a, b) {
            let questionsA = parseInt(a.querySelector(".right-info p:first-child b").textContent);
            let questionsB = parseInt(b.querySelector(".right-info p:first-child b").textContent);
            return questionsB - questionsA;
        });
    } else if (sortValue === "min-questions") {
        // Sort in ascending order based on the number of questions
        sortedQuizzes.sort(function (a, b) {
            let questionsA = parseInt(a.querySelector(".right-info p:first-child b").textContent);
            let questionsB = parseInt(b.querySelector(".right-info p:first-child b").textContent);
            return questionsA - questionsB;
        });
    }

    // Append the sorted quizzes back to the container
    for (let i = 0; i < sortedQuizzes.length; i++) {
        quizSection.appendChild(sortedQuizzes[i]);
    }
}
