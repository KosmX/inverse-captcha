let countdown;
const clock = document.getElementById('clock');

const endTime = clock.textContent

timer(endTime)

function timer(date){
        // countdown holds the entire timer functionality 
        clockAction(date);
        countdown = setInterval(()=>{
                clockAction(date);
        }, 1000);// every 1 second
}

function clockAction(date) {
        const now = Date.now(); // current date and time
        const differenceInTime = date - now; // millis until
        if(differenceInTime < 0){
                location.reload();
                return;
        }

  clock.textContent = Math.floor(differenceInTime/1000) + " s";
}

/*
 * CTF players,
 * This is just a client-side warning script, the server will enforce the time limit based on its clock.
 * Don't waste your time killing this script, you can, but that won't solve the challenge.
 */

