// script.js

const slots = document.querySelectorAll('.slot');
let selectedSlot = null;

slots.forEach(slot => {
    slot.addEventListener('click', () => {
        if (slot.classList.contains('booked')) return;
        
        if (selectedSlot) {
            selectedSlot.classList.remove('selected');
        }

        selectedSlot = slot;
        slot.classList.add('selected');
    });
});

document.getElementById('bookSlotBtn').addEventListener('click', () => {
    if (selectedSlot) {
        alert(`Booking slot ${selectedSlot.dataset.number}`);
        // Code to handle booking, e.g., sending data to the server
    }
});

/**
 * 
 */