-- Selecteer alle orders (ORDERID, CUSTOMER.COMPANYNAME) voor het product “Tofu” waar de ‘freight’ kosten tussen 25 en 50 waren.

SELECT Orders.OrderID, Customers.CompanyName FROM Orders INNER JOIN Customers ON Customers.CustomerID=Orders.CustomerID INNER JOIN [Order Details] ON [Order Details].OrderID=Orders.OrderID INNER JOIN Products ON Products.ProductID=[Order Details].ProductID WHERE Products.ProductName='Tofu' AND Orders.Freight BETWEEN 25 AND 50;
