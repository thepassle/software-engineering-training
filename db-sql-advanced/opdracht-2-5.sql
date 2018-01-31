-- Selecteer de plaatsnamen waarin zowel klanten als werknemers wonen. Gebruik een subquery voor deze opdracht.

SELECT DISTINCT Customers.City FROM Customers WHERE Customers.City IN (SELECT DISTINCT Employees.City FROM Employees);
