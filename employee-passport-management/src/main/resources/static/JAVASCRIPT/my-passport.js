const myPassportNo = document.getElementById("myPassportNo");
const myIssueDate = document.getElementById("myIssueDate");
const myExpiryDate = document.getElementById("myExpiryDate");
const myNationality = document.getElementById("myNationality");

const updatePassportBtn = document.getElementById("updatePassportBtn");
const passportMessage = document.getElementById("passportMessage");



async function loadMyPassport() {

    try {

        const response = await fetch("/passports/me", {

            method: "GET",

            credentials: "include"

        });

        if (!response.ok) {

            passportMessage.innerText =
                "Unable to load passport. Status: " + response.status;

            passportMessage.style.color = "red";

            return;
        }

        const passport = await response.json();

        myPassportNo.value = passport.passportNo;
        myIssueDate.value = passport.issueDate;
        myExpiryDate.value = passport.expiryDate;
        myNationality.value = passport.nationality;

    } catch (error) {

        passportMessage.innerText = "Server connection failed";
        passportMessage.style.color = "red";

        console.error(error);
    }
}



updatePassportBtn.addEventListener("click", async function () {

    const passport = {

        passportNo: myPassportNo.value,

        issueDate: myIssueDate.value,

        expiryDate: myExpiryDate.value,

        nationality: myNationality.value

    };

    try {

        const response = await fetch("/passports/me", {

            method: "PUT",

            headers: {
                "Content-Type": "application/json"
            },

            credentials: "include",

            body: JSON.stringify(passport)
        });

        if (response.ok) {

            passportMessage.innerText =
                "Passport updated successfully";

            passportMessage.style.color = "green";

            loadMyPassport();

        } else {

            const error = await response.text();

            passportMessage.innerText =
                "Update failed. Status: " +
                response.status + " " + error;

            passportMessage.style.color = "red";
        }

    } catch (error) {

        passportMessage.innerText = "Server connection failed";
        passportMessage.style.color = "red";

        console.error(error);
    }
});


loadMyPassport();