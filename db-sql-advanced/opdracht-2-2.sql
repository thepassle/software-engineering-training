-- Selecteer alle orders voor “Pavlova” met een salesresultaat van minstens 800.

USE Northwind; 

-- mssql> select * from Products WHERE ProductName='Pavlova'
-- ProductID  ProductName  SupplierID  CategoryID  QuantityPerUnit   UnitPrice  UnitsInStock  UnitsOnOrder  ReorderLevel  Discontinued
-- ---------  -----------  ----------  ----------  ----------------  ---------  ------------  ------------  ------------  ------------
-- 16         Pavlova      7           3           32 - 500 g boxes  17.45      29            0             10            false       

SELECT
Orders.*, [Order Details].UnitPrice*[Order Details].Quantity*(1-[Order Details].Discount)
FROM
Orders
INNER JOIN
[Order Details] ON Orders.OrderID=[Order Details].OrderID
INNER JOIN
Products ON [Order Details].ProductID=Products.ProductID
WHERE Products.ProductName='Pavlova' AND [Order Details].UnitPrice*[Order Details].Quantity*(1-[Order Details].Discount)>=800
;
