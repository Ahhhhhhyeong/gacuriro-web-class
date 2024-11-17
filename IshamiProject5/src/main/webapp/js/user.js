// Function to open the modal and populate it with user data
function openPopup(email, name, level, id) {
	const modal = document.getElementById("popupModal"); // Get the modal element
	document.getElementById("popupContent").innerHTML = `
        <h3>Update User</h3>
        <form action="user" method="post">
            <input type="hidden" name="action" value="update"> <!-- Hidden input for action type -->
            <input type="hidden" name="id" value="${id}"> <!-- Hidden input for user ID -->
            <label>Email:</label>
            <input type="text" name="email" value="${email}" readonly><br> <!-- Display user email as read-only -->
            <label>Name:</label>
            <input type="text" name="name" value="${name}"><br> <!-- Input for user name -->
            <label>Level:</label>
            <input type="text" name="level" value="${level}"><br> <!-- Input for user level -->
            <button type="submit">Save</button> <!-- Submit button to save changes -->
            <button type="button" onclick="closePopup()">Cancel</button> <!-- Button to cancel and close modal -->
        </form>
    `;
	modal.style.display = "block"; // Display the modal
}

// Function to close the modal
function closePopup() {
	const modal = document.getElementById("popupModal"); // Get the modal element
	modal.style.display = "none"; // Hide the modal
}

// Function to close the modal when clicking outside of it
window.onclick = function(event) {
	const modal = document.getElementById("popupModal"); // Get the modal element
	if (event.target == modal) {
		modal.style.display = "none"; // Hide the modal if the click is outside the modal content
	}
}

// Function to handle the delete button click
function deleteUser(userId) {
	if (confirm("Are you sure you want to delete this user?")) { // Confirm the delete action
		const form = document.createElement("form"); // Create a new form element
		form.method = "post"; // Set the form method to POST
		form.action = "/user"; // Set the form action URL

		const actionInput = document.createElement("input"); // Create a hidden input for action type
		actionInput.type = "hidden";
		actionInput.name = "action";
		actionInput.value = "delete"; // Set action value to "delete"
		form.appendChild(actionInput);

		const idInput = document.createElement("input"); // Create a hidden input for user ID
		idInput.type = "hidden";
		idInput.name = "id";
		idInput.value = userId; // Set the user ID
		form.appendChild(idInput);

		document.body.appendChild(form); // Add the form to the document body
		form.submit(); // Submit the form to delete the user
	}
}
