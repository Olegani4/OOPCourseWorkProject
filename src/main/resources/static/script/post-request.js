// Function to create a quiz
async function createQuiz(authorName, authorEmail) {
    const quizName = document.getElementById("name").value;

    // Check if the quiz name is not empty and at least one question is added
    if (quizName !== "" && quizCreatorQuestions.length > 0) {
        const quizTopic = document.getElementById("topics").value;
        const quizBackground = document.getElementById("backgrounds").value;
        const quizQueTimeLimit = document.getElementById("time-limit").value;

        // Create a dictionary object with quiz details
        const quizDict = {
            topic_title: quizTopic,
            name: quizName,
            questions: quizCreatorQuestions,
            questions_num: quizCreatorQuestions.length,
            likes: 0,
            author_name: authorName,
            author_email: authorEmail,
            background_hex: quizBackground,
            text_color: "#000000",
            time_limit_sec: quizQueTimeLimit,
        };

        try {
            // Send a POST request to create the quiz on the server
            const response = await fetch("/quiz", {
                method: "POST",
                headers: {
                    Accept: "application/json",
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(quizDict),
            });

            // If the server response is not successful, display an error message
            if (!response.ok) {
                alert(
                    "Server response error: " +
                    response.status +
                    "\nPlease check your connection and try again!"
                );
                window.location.reload();
            }
        } catch (e) {
            window.location.reload();
        }

        window.location.reload();
    } else {
        alert("Please fill the quiz title field and add at least one question!");
    }
}

// Function to handle user sign-in
async function signIn() {
    // Function to validate an email using a regular expression pattern
    function validateEmail(email) {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    }

    const login = document.getElementById("login").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const passwordAgain = document.getElementById("password-again").value;

    // Check if the email is valid
    if (validateEmail(email)) {
        // Check if all required fields are filled
        if (login !== "" && email !== "" && password !== "" && passwordAgain !== "") {
            // Check if the passwords match
            if (password === passwordAgain) {
                const userDict = {
                    login: login,
                    email: email,
                    password: password,
                };
                try {
                    // Send a POST request to create a new user account
                    const response = await fetch("/sign-in", {
                        method: "POST",
                        headers: {
                            Accept: "application/json",
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(userDict),
                    });

                    // If the server response is not successful, display an error message
                    if (!response.ok) {
                        alert(
                            "Server response error: " +
                            response.status +
                            "\nPlease check your connection and try again!"
                        );
                        window.location.reload();
                    }
                    window.open("/log-in", "_self");
                } catch (e) {
                    window.location.reload();
                }
            } else {
                alert("Passwords do not match!");
            }
        } else {
            alert("Please fill all fields!");
        }
    } else {
        alert("Please enter a valid email!");
    }
}

// Function to handle user login
async function logIn() {
    const login = document.getElementById("login").value;
    const password = document.getElementById("password").value;

    // Check if both login and password fields are filled
    if (login !== "" && password !== "") {
        const userDict = {
            login: login,
            password: password,
        };
        try {
            // Send a POST request to log in the user
            const response = await fetch("/log-in", {
                method: "POST",
                headers: {
                    Accept: "application/json",
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(userDict),
            });

            // If the server response is not successful, display an error message
            if (!response.ok) {
                alert(
                    "Server response error: " +
                    response.status +
                    "\nPlease check your connection and try again!"
                );
                window.location.reload();
            }
            window.open("/log-in-id", "_self");
        } catch (e) {
            window.location.reload();
        }

    } else {
        alert("Please fill all fields!");
    }
}

// Function to find a quiz
async function findQuiz() {
    const quizName = document.getElementById("quiz-name").value;
    const authorName = document.getElementById("author-name").value;

    const findQuizDict = {
        quiz_name: quizName,
        author_name: authorName,
    };

    try {
        // Send a POST request to search for a quiz
        const response = await fetch("/find-quiz", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(findQuizDict),
        });

        // If the server response is not successful, display an error message
        if (!response.ok) {
            alert(
                "Server response error: " +
                response.status +
                "\nPlease check your connection and try again!"
            );
            window.location.reload();
        }

        // Extract the user ID from the URL and open the found quiz page
        const url = window.location.href;
        const userId = url.split("/").pop();
        window.open("/found-quiz/" + userId, "_self");
    } catch (e) {
        window.location.reload();
    }
}

// Function to retrieve quizzes by author name
async function authorQuizzes(authorName) {
    const findQuizDict = {
        quiz_name: "",
        author_name: authorName,
    };

    try {
        // Send a POST request to retrieve quizzes by author name
        const response = await fetch("/find-quiz", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(findQuizDict),
        });

        // If the server response is not successful, display an error message
        if (!response.ok) {
            alert(
                "Server response error: " +
                response.status +
                "\nPlease check your connection and try again!"
            );
            window.location.reload();
        }

        // Extract the user ID from the URL and open the found quiz page
        const url = window.location.href;
        const userId = url.split("/").pop();
        window.open("/found-quiz/" + userId, "_self");
    } catch (e) {
        window.location.reload();
    }
}

// Function to delete a quiz
async function deleteQuiz(quizId) {
    try {
        // Send a POST request to delete the specified quiz
        const response = await fetch("/delete-quiz", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ quiz_id: quizId }),
        });

        // If the server response is not successful, display an error message
        if (!response.ok) {
            alert(
                "Server response error: " +
                response.status +
                "\nPlease check your connection and try again!"
            );
            window.location.reload();
        }
        window.location.reload();
    } catch (e) {
        window.location.reload();
    }
}
