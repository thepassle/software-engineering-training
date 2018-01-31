-- Selecteer alle klanten (CUSTOMERID, COMPANYNAME) die in London wonen en minder dan 5 orders hebben gedaan. 
-- Orden het resultaat op aantal geplaatste orders.

USE Northwind; 

SELECT
Customers.CustomerID, Customers.CompanyName, COUNT(Orders.CustomerID)
FROM
Customers
INNER JOIN
Orders ON Orders.CustomerID=Customers.CustomerID
WHERE
Customers.City='London'
GROUP BY
Customers.CustomerID, Customers.CompanyName
HAVING
COUNT(Orders.CustomerID)<5
ORDER BY
COUNT(Orders.CustomerID)
;

USE Northwind; SELECT Customers.CustomerID, Customers.CompanyName, COUNT(Orders.CustomerID) FROM Customers INNER JOIN Orders ON Orders.CustomerID=Customers.CustomerID WHERE Customers.City='London' GROUP BY Customers.CustomerID, Customers.CompanyName HAVING COUNT(Orders.CustomerID)<5 ORDER BY COUNT(Orders.CustomerID) ;