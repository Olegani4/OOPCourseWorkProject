function expandTimeLineWidth(duration) {   // Set duration in seconds
    duration = duration * 1000; // Convert to milliseconds
    const timeLime = document.getElementById("time-line"); // Get the time-line element
    const startTime = Date.now(); // Get the current timestamp
    const startWidth = timeLime.offsetWidth; // Get the current width of the time-line element
    const targetWidth = timeLime.parentElement.offsetWidth; // Get the width of the parent element of the time-line

    function animate() {
        const elapsedTime = Date.now() - startTime; // Calculate the elapsed time
        const progress = elapsedTime / duration; // Calculate the progress as a fraction of the total duration
        const newWidth = startWidth + (targetWidth - startWidth) * progress; // Calculate the new width based on the progress

        timeLime.style.width = `${newWidth - 18}px`; // Set the width of the time-line element

        if (elapsedTime < duration) {
            requestAnimationFrame(animate); // If the animation is not complete, request the next animation frame
        } else {
            let url = window.location.href; // Get the current URL
            const questionInd = parseInt(url.replace(/\/[^/]+$/, "").split("/").pop()); // Extract the question index from the URL
            url = window.location.href;
            const modifiedUrl = url.replace(/\/[^/]+\/[^/]+$/, ""); // Remove the current question index from the URL
            window.open(modifiedUrl + "/" + String(questionInd + 1) + "/" + String(score), "_self"); // Open the next question URL in the same tab or window
        }
    }

    animate(); // Start the animation
}


function checkAnswer(isCorrect) {
    if(isCorrect){score++;} // Increment the score if the answer is correct
    let url = window.location.href; // Get the current URL
    const questionInd = parseInt(url.replace(/\/[^/]+$/, "").split("/").pop()); // Extract the question index from the URL
    url = window.location.href;
    const modifiedUrl = url.replace(/\/[^/]+\/[^/]+$/, ""); // Remove the current question index from the URL
    window.open(modifiedUrl + "/" + String(questionInd + 1) + "/" + String(score), "_self"); // Open the next question URL in the same tab or window
}


function getPhrase(score, totalScore, element) {
    const percentage = (score / totalScore * 100).toFixed(0); // Calculate the percentage score

    element = document.getElementById(element); // Get the element to display the phrase

    let phrase = "";

    // Determine the appropriate phrase based on the percentage score
    if (percentage >= 90) {
        phrase = "Outstanding work";
    } else if (percentage >= 80 && percentage < 90) {
        phrase = "Wow! It was amazing";
    } else if (percentage >= 70 && percentage < 80) {
        phrase = "Great job";
    } else if (percentage >= 60 && percentage < 70) {
        phrase = "Good effort";
    } else if (percentage >= 50 && percentage < 60) {
        phrase = "Keep working hard";
    } else if (percentage >= 40 && percentage < 50) {
        phrase = "You're making progress";
    } else if (percentage >= 30 && percentage < 40) {
        phrase = "Don't give up";
    } else if (percentage >= 20 && percentage < 30) {
        phrase = "You can do better";
    } else if (percentage >= 10 && percentage < 20) {
        phrase = "Keep practicing";
    } else {
        phrase = "Keep trying";
    }

    element.innerHTML = phrase; // Set the innerHTML of the element to display the phrase
}
