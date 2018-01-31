-- Selecteer alle regio’s (REGIONDESCRIPTION) waarin het product “Chocolade” is verkocht.


USE Northwind; 

SELECT DISTINCT
Region.RegionDescription
FROM
Region
INNER JOIN
Territories ON Territories.RegionID=Region.RegionID
INNER JOIN
EmployeeTerritories ON EmployeeTerritories.TerritoryID=Territories.TerritoryID
INNER JOIN
Employees ON EmployeeTerritories.EmployeeID=Employees.EmployeeID
INNER JOIN
Orders ON Orders.EmployeeID=Employees.EmployeeID
INNER JOIN
[Order Details] ON [Order Details].OrderID=Orders.OrderID
INNER JOIN
Products ON Products.ProductID=[Order Details].ProductID
WHERE
ProductName='Chocolade';


-- SELECT DISTINCT Region.RegionDescription FROM Region INNER JOIN Territories ON Territories.RegionID=Region.RegionID INNER JOIN EmployeeTerritories ON EmployeeTerritories.TerritoryID=Territories.TerritoryID INNER JOIN Employees ON EmployeeTerritories.EmployeeID=Employees.EmployeeID INNER JOIN Orders ON Orders.EmployeeID=Employees.EmployeeID INNER JOIN [Order Details] ON [Order Details].OrderID=Orders.OrderID INNER JOIN Products ON Products.ProductID=[Order Details].ProductID WHERE ProductName='Chocolade';