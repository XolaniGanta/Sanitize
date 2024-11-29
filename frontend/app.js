const baseUrl = "http://localhost:8080/api/sensitive-words"; 

// Add Sensitive Words
document.getElementById("add-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const words = document.getElementById("add-input").value.split(",");
    try {
        const response = await fetch(`${baseUrl}/add`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ words }),
        });

        if (!response.ok) {
            throw new Error(`Server error`);
        }

        const data = await response.json();
        document.getElementById("add-output").innerText = `Words added successfully, number of words saved: "${data.numOfWordsSaved}"`
    } catch (error) {
        alert("Error adding words: " + error.message);
    }
});

// Get All Sensitive Words
document.getElementById("get-all-button").addEventListener("click", async () => {
    try {
        const response = await fetch(`${baseUrl}/all`);
        const data = await response.json();

        const words = data.sensitiveWords;
        document.getElementById("all-words-output").innerText = words.join(", ");
    } catch (error) {
        alert("Error fetching words: " + error.message);
    }
});


// Sanitize Input
document.getElementById("sanitize-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const inputString = document.getElementById("sanitize-input").value;
    try {
        const response = await fetch(`${baseUrl}/sanitize`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ inputString }),
        });

        if (!response.ok) {
            throw new Error(`Server error: ${response.statusText}`);
        }

        const data = await response.json();
        document.getElementById("sanitize-output").innerText = data.sanitizedWord;
    } catch (error) {
        alert("Error sanitizing text: " + error.message);
    }
});


// Delete Sensitive Word
document.getElementById("delete-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("delete-id").value;
    try {
        await fetch(`${baseUrl}/delete/${id}`, { method: "DELETE" });
        alert("Word deleted successfully.");
    } catch (error) {
        alert("Error deleting word: " + error.message);
    }
});

// Update Sensitive Word
document.getElementById("update-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("update-id").value;
    const newWord = document.getElementById("update-word").value;
    try {
        const response = await fetch(`${baseUrl}/update`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ id, newWord }),
        });

        // Check if the response is successful
        if (!response.ok) {
            // If not successful, throw an error with the response message
            const errorData = await response.json();
            throw new Error(errorData.message || "An error occurred while updating the word.");
        }

        // If successful, extract the data and display it
        const data = await response.json();
        document.getElementById("updateWordOutput").innerText = `Word updated successfully. ID: ${id}, New Word: "${data.newWord}", Old Word: "${data.oldWord}"`;
    } catch (error) {
        // Display the error message from the server or default error message
        alert("Error updating word: " + error.message);
    }
});

