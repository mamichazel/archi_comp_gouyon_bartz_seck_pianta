function openFlightDetailsModal(button) {
    let flightId = button.getAttribute("data-flight-id");
    let numeroVol = button.getAttribute("data-numero-vol");
    let compagnie = button.getAttribute("data-compagnie");
    let modele = button.getAttribute("data-modele");
    let depart = button.getAttribute("data-depart");
    let arrivee = button.getAttribute("data-arrivee");
    let statut = button.getAttribute("data-statut");

    document.getElementById("modalNumeroVol").textContent = numeroVol;
    document.getElementById("modalCompagnie").textContent = compagnie;
    document.getElementById("modalModele").textContent = modele;
    document.getElementById("modalDepart").textContent = depart;
    document.getElementById("modalArrivee").textContent = arrivee;
    document.getElementById("modalStatut").textContent = statut;

    let passengerListBtn = document.getElementById("passengerListBtn");
    passengerListBtn.setAttribute("href", "/flights/" + flightId + "/passengers");

    let modal = new bootstrap.Modal(document.getElementById("flightDetailsModal"));
    modal.show();
}

function showLuggages(button) {
    let passengerId = button.getAttribute("data-passenger-id");

    fetch(`/passengers/${passengerId}/luggages`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Erreur lors de la récupération des bagages");
            }
            return response.json();
        })
        .then(luggages => {
            let tableBody = document.getElementById("modal-luggages-body");
            tableBody.innerHTML = ""; // Vide la table avant d'afficher les nouveaux résultats

            if (luggages.length === 0) {
                tableBody.innerHTML = "<tr><td colspan='2'>Aucun bagage</td></tr>";
            } else {
                luggages.forEach(luggage => {
                    let row = `<tr>
                                  <td>${luggage.type}</td>
                                  <td>${luggage.poids}</td>
                               </tr>`;
                    tableBody.innerHTML += row;
                });
            }

            // Met à jour le nom du passager dans la modale
            document.getElementById("modal-passenger-name").innerText = button.closest("tr").querySelector("td:first-child").innerText;

            // Affiche la modale Bootstrap
            let modal = new bootstrap.Modal(document.getElementById("luggageModal"));
            modal.show();
        })
        .catch(error => alert(error.message));
}