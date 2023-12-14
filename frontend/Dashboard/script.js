const backdrop = document.querySelector(".modal-backdrop");

// Function to open a modal
function openModal(modal) {
    modal.style.display = "block";
    backdrop.style.display = "block"; // Show the backdrop
}

// Function to close a modal
function closeModal(modal) {
    modal.style.display = "none";
    backdrop.style.display = "none"; // Hide the backdrop
}

// Event listeners for opening modals
document.querySelector(".open-button").addEventListener("click", () => openModal(document.querySelector(".modal")));
document.getElementById("showInternetErrorBtn").addEventListener("click", () => openModal(document.getElementById("internetErrorModal")));
document.getElementById("showInternetErrorBtn").addEventListener("click", () => openModal(document.getElementById("internetErrorModal")));
document.getElementById("refreshButton").addEventListener("click", () => testUpdate());

// Event listeners for closing modals
document.querySelector(".modal .close-button").addEventListener("click", () => closeModal(document.querySelector(".modal")));
document.getElementById("internetErrorModal").querySelector(".close-button").addEventListener("click", () => closeModal(document.getElementById("internetErrorModal")));
document.getElementById("generalErrorModal").querySelector(".close-button").addEventListener("click", () => closeModal(document.getElementById("generalErrorModal")));

// Close modal when clicking on the backdrop
backdrop.addEventListener("click", () => {
    // Close all modals
    document.querySelectorAll(".modal").forEach(modal => closeModal(modal));
});
var total = 0;

function testUpdate() {
    const displayElement = document.getElementById("totalFlex");
    total += 5;
    displayElement.innerText = `\$${total}.00`;
}
