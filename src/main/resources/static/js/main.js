function toggleFavorite(starElement, employeeIndex) {
    const isFavorite = starElement.classList.contains('fas');
    if (isFavorite) {
        starElement.classList.remove('fas');
        starElement.classList.add('far');
        Cookies.remove('favorite_' + employeeIndex); // Using js-cookie
    } else {
        starElement.classList.add('fas');
        starElement.classList.remove('far');
        Cookies.set('favorite_' + employeeIndex, 'true', { expires: 7000 }); // Using js-cookie
    }
}

document.addEventListener("DOMContentLoaded", function() {
    const stars = document.querySelectorAll('.fa-star');
    stars.forEach((star) => {
        const employeeId = star.getAttribute('data-id'); // Get the employee ID from data-id attribute
        if (Cookies.get('favorite_' + employeeId) === 'true') { // Check the cookie using employee ID
            star.classList.remove('far');
            star.classList.add('fas');
        }
    });
    const favoriteHeader = document.querySelectorAll("th")[11]; // Adjust the index if necessary
});

document.addEventListener("DOMContentLoaded", function() {
    const searchInput = document.getElementById('searchInput');
    searchInput.addEventListener('keyup', function() {
        let filter = searchInput.value.toLowerCase();
        let table = document.querySelector("table");
        let tr = table.getElementsByTagName("tr");
        for (let i = 1; i < tr.length; i++) {
            let tds = tr[i].getElementsByTagName("td");
            let found = false;
            for (let j = 0; j < tds.length; j++) {
                if (tds[j].textContent.toLowerCase().indexOf(filter) > -1) {
                    found = true;
                    break;
                }
            }
            tr[i].style.display = found ? "" : "none";
        }
    });

    // Add sorting functionality
    let headers = document.querySelectorAll("th");
    headers.forEach(function(header, index) {
        header.addEventListener("click", function() {
            sortTableByColumn(header, index); // Pass the header as an argument
        });
    });

function sortTableByColumn(header, column, forceDesc = false) {
const table = document.querySelector("table");
const tbody = table.querySelector("tbody");
const rows = Array.from(tbody.querySelectorAll("tr"));
let isAscending = header.getAttribute("data-order") === "asc";
if (forceDesc) isAscending = false; // Force descending order if specified

const sortedRows = rows.sort(function(a, b) {
    // Check if sorting the favorite column
    if (column === 11) { // Assuming the favorite column is the 12th column (0-based index is 11)
        const aFavorite = a.querySelector(`td:nth-child(${column + 1}) i`).classList.contains('fas');
        const bFavorite = b.querySelector(`td:nth-child(${column + 1}) i`).classList.contains('fas');
        return aFavorite === bFavorite ? 0 : aFavorite ? -1 : 1;
    } else {
        // Text-based sorting for other columns
        const aColText = a.querySelector(`td:nth-child(${column + 1})`).textContent.trim();
        const bColText = b.querySelector(`td:nth-child(${column + 1})`).textContent.trim();
        return aColText > bColText ? (isAscending ? 1 : -1) : (aColText < bColText ? (isAscending ? -1 : 1) : 0);
    }
});

// Apply the new sort order for the header
if (!forceDesc) { // Skip this if we're forcing descending order on load
    headers.forEach(h => h.removeAttribute("data-order")); // Reset other headers
    header.setAttribute("data-order", isAscending ? "desc" : "asc");
}

// Re-append rows in sorted order
sortedRows.forEach(row => tbody.appendChild(row));
}
const favoriteHeader = document.querySelectorAll("th")[11]; // Adjust the index if necessary
sortTableByColumn(favoriteHeader, 11, true); // Force sorting in descending order for favorites on page load
});


function toggleTextSize() {
    var bodyElement = document.body;
    var iconElement = document.getElementById('toggleTextSizeIcon');
    bodyElement.classList.toggle('text-large');

    if (bodyElement.classList.contains('text-large')) {
        iconElement.classList.remove('fa-text-height');
        iconElement.classList.add('fa-text-width'); // Change icon to indicate normal text
        Cookies.set('textSize', 'large', { expires: 7 }); // Set cookie for 7 days
    } else {
        iconElement.classList.remove('fa-text-width');
        iconElement.classList.add('fa-text-height'); // Change icon to indicate large text
        Cookies.set('textSize', 'normal', { expires: 7 }); // Set cookie for 7 days
    }
}

// Check for text size cookie on page load
window.onload = function() {
    var textSizePreference = Cookies.get('textSize');
    var iconElement = document.getElementById('toggleTextSizeIcon');
    if (textSizePreference === 'large') {
        document.body.classList.add('text-large');
        iconElement.classList.remove('fa-text-height');
        iconElement.classList.add('fa-text-width');
    }
};