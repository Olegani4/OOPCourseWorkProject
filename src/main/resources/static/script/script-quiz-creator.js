let quizCreatorQuestions = [];

/* Modal */
function openDialog(dialogId) {
    // Get the dialog element
    let dialog = document.getElementById(dialogId);
    // Show the dialog as a modal
    dialog.showModal();

    // Add a click event listener to the dialog
    dialog.addEventListener("click", (e) => {
        // Get the dimensions of the dialog
        const dialogDimensions = dialog.getBoundingClientRect();
        // Check if the click event occurred outside the dialog
        if (
            e.clientX < dialogDimensions.left ||
            e.clientX > dialogDimensions.right ||
            e.clientY < dialogDimensions.top ||
            e.clientY > dialogDimensions.bottom
        ) {
            // Close the dialog
            closeDialog(dialogId);
        }
    });
}

function closeDialog(dialogId) {
    // Close the dialog by calling the close() method
    document.getElementById(dialogId).close();

    // Clear the input fields and reset styles based on the dialog type
    if (dialogId === "dialog-true-false") {
        document.getElementById("question-tf").value = "";
        const trueCb = document.getElementById("answer-tf-true");
        const falseCb = document.getElementById("answer-tf-false");
        trueCb.style.backgroundColor = "rgb(255, 255, 255)";
        trueCb.style.color = "black";
        falseCb.style.backgroundColor = "rgb(255, 255, 255)";
        falseCb.style.color = "black";
    } else {
        document.getElementById("question-mc").value = "";
        for (let i = 1; i <= 4; i++) {
            document.getElementById(`a-choice-${i}`).value = "";
        }
        document.getElementById("correct-a-choice").value = "";
    }
}

function trueFalseCheckbox(cbOption) {
    // Get the checkbox elements
    const trueCb = document.getElementById("answer-tf-true");
    const falseCb = document.getElementById("answer-tf-false");

    // Update the styles of the checkboxes based on the selected option
    if (cbOption) {
        trueCb.style.backgroundColor = "rgb(0, 0, 255)";
        trueCb.style.color = "white";
        falseCb.style.backgroundColor = "rgb(255, 255, 255)";
        falseCb.style.color = "black";
    } else {
        falseCb.style.backgroundColor = "rgb(0, 0, 255)";
        falseCb.style.color = "white";
        trueCb.style.backgroundColor = "rgb(255, 255, 255)";
        trueCb.style.color = "black";
    }
}

function addQuestion(type) {
    // Check the type of the question (True/False or Multiple Choice)
    if (type === "tf") {
        const question = document.getElementById("question-tf").value;
        const falseCb = document.getElementById("answer-tf-false");
        // Validate if the question field is filled
        if (question === "") {
            alert("Please fill the question field!");
            return;
        }
        const trueCb = document.getElementById("answer-tf-true");
        let answer = "";
        // Determine the selected answer based on checkbox styles
        if (trueCb.style.backgroundColor === "rgb(0, 0, 255)") {
            answer = "True";
        } else if (falseCb.style.backgroundColor === "rgb(0, 0, 255)") {
            answer = "False";
        } else {
            alert("Please choose an answer!");
            return;
        }
        const questionDict = {
            type: "True/False",
            content: question,
            choice_of_answers: null,
            answer: answer,
        };
        quizCreatorQuestions.push(questionDict);
        addQuestionToList(questionDict);
        closeDialog("dialog-true-false");
    } else {
        const question = document.getElementById("question-mc").value;
        // Validate if the question field is filled
        if (question === "") {
            alert("Please fill the question field!");
            return;
        }
        let choice_of_answers = [];
        for (let i = 1; i <= 4; i++) {
            const choice = document.getElementById(`a-choice-${i}`).value;
            // Add the non-empty answer choices to the array
            if (choice !== "") {
                choice_of_answers.push(choice);
            }
        }
        // Validate if at least 2 answer fields are filled
        if (choice_of_answers.length < 2) {
            alert("Please fill at least 2 answer fields!");
            return;
        }
        const answer = document.getElementById("correct-a-choice").value;
        // Validate if the correct answer field is filled
        if (answer === "") {
            alert("Please fill the correct answer field!");
            return;
        }
        let answerFlag = false;
        // Check if the correct answer is one of the answer choices
        for (let i = 0; i < choice_of_answers.length; i++) {
            if (choice_of_answers[i] === answer) {
                answerFlag = true;
            }
        }
        if (!answerFlag) {
            alert("The correct answer must be one of the answer choices!");
            return;
        }
        const questionDict = {
            type: "Multiple choice",
            content: question,
            choice_of_answers: choice_of_answers,
            answer: answer,
        };
        quizCreatorQuestions.push(questionDict);
        addQuestionToList(questionDict);
        closeDialog("dialog-mult-choice");
    }
}

function addQuestionToList(questionDict) {
    // Get the question list element
    const questionList = document.getElementById("question-list");

    // Create the elements for displaying the question details
    const question = document.createElement("div");
    question.classList.add("question");

    const questionContent = document.createElement("div");
    questionContent.innerHTML = `Question ${quizCreatorQuestions.length}`;

    const questionType = document.createElement("p");
    questionType.innerHTML = `Type: <b>${questionDict.type}</b>`;

    const questionContentP = document.createElement("p");
    questionContentP.innerHTML = `Question: <b>${questionDict.content}</b>`;

    const questionChoice = document.createElement("p");
    if (questionDict.choice_of_answers !== null) {
        let coa = "";
        for (let i = 0; i < questionDict.choice_of_answers.length; i++) {
            coa += questionDict.choice_of_answers[i] + ", ";
        }
        coa = coa.slice(0, -2);
        questionChoice.innerHTML = `Choice of answers: <b>${coa}</b>`;
    }

    const questionAnswer = document.createElement("p");
    questionAnswer.innerHTML = `Answer: <b>${questionDict.answer}</b>`;

    const questionCross = document.createElement("span");
    questionCross.classList.add("cross");
    questionCross.innerHTML = "╳";

    const questionEdit = document.createElement("span");
    questionEdit.classList.add("edit");
    questionEdit.innerHTML = "&nbsp;";

    // Append the elements to the question container
    question.appendChild(questionContent);
    question.appendChild(questionType);
    question.appendChild(questionContentP);
    if (questionDict.choice_of_answers !== null) {
        question.appendChild(questionChoice);
    }
    question.appendChild(questionAnswer);
    question.appendChild(questionCross);
    question.appendChild(questionEdit);

    // Append the question container to the question list
    questionList.appendChild(question);

    // Add a click event listener to the cross icon to remove the question
    questionCross.addEventListener("click", () => {
        question.remove();
    });
}
