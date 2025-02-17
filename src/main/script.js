const dealerships = [
    { name: "Autos Madrid", location: "Madrid" },
    { name: "Barcelona Motors", location: "Barcelona" },
    { name: "Valencia Cars", location: "Valencia" }
];

const cars = [
    { name: "Toyota Corolla", km: "50,000 km", dealership: "Autos Madrid" },
    { name: "Ford Mustang", km: "30,000 km", dealership: "Autos Madrid" },
    { name: "Tesla Model 3", km: "20,000 km", dealership: "Autos Madrid" },
    { name: "Honda Civic", km: "40,000 km", dealership: "Barcelona Motors" },
    { name: "BMW M3", km: "25,000 km", dealership: "Barcelona Motors" },
    { name: "Audi A4", km: "35,000 km", dealership: "Barcelona Motors" },
    { name: "Mercedes-Benz C-Class", km: "27,000 km", dealership: "Barcelona Motors" },
    { name: "Volkswagen Golf", km: "45,000 km", dealership: "Valencia Cars" },
    { name: "Nissan Qashqai", km: "38,000 km", dealership: "Valencia Cars" },
    { name: "Peugeot 208", km: "33,000 km", dealership: "Valencia Cars" },
    { name: "Hyundai Tucson", km: "29,000 km", dealership: "Valencia Cars" }
];

// Asociar coches a concesionarios
dealerships.forEach(dealer => {
    dealer.cars = cars.filter(car => car.dealership === dealer.name);
});

// Mostrar lista de coches
document.addEventListener("DOMContentLoaded", function () {
    const carList = document.getElementById("car-list");
    if (carList) {
        cars.forEach(car => {
            const div = document.createElement("div");
            div.classList.add("car-item");
            div.innerText = `${car.name} - ${car.km} (${car.dealership})`;
            carList.appendChild(div);
        });
    }
});

// Mostrar lista de concesionarios
document.addEventListener("DOMContentLoaded", function () {
    const dealershipList = document.getElementById("dealership-list");
    if (dealershipList) {
        dealerships.forEach(dealer => {
            const div = document.createElement("div");
            div.classList.add("dealership-item");
            div.innerText = `${dealer.name} - ${dealer.location}`;
            div.onclick = function () {
                localStorage.setItem("selectedDealership", JSON.stringify(dealer));
                window.location.href = "dealership.html";
            };
            dealershipList.appendChild(div);
        });
    }
});

// Mostrar detalles de un concesionario
document.addEventListener("DOMContentLoaded", function () {
    const dealershipPage = document.getElementById("dealership-name");
    const dealershipCars = document.getElementById("dealership-cars");
    if (dealershipPage && dealershipCars) {
        const selectedDealership = JSON.parse(localStorage.getItem("selectedDealership"));
        if (selectedDealership) {
            dealershipPage.innerText = `${selectedDealership.name} - ${selectedDealership.location}`;
            const carsInDealer = cars.filter(car => car.dealership === selectedDealership.name);
            carsInDealer.forEach(car => {
                const div = document.createElement("div");
                div.classList.add("car-item");
                div.innerText = `${car.name} - ${car.km}`;
                dealershipCars.appendChild(div);
            });
        }
    }
});
