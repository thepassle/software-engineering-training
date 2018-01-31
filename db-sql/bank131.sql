-- SELECT Client.AddressID, Client.FamilyName, Address.ZipCode, Loan.Amount FROM Client INNER JOIN Address ON Client.AddressID=Address.ID INNER JOIN Loan ON Client.ID=Loan.ClientID WHERE Address.ZipCode > '3000' AND Address.ZipCode < '4000' AND Loan.Amount > 2500

SELECT 
	Client.AddressID, Client.FamilyName, Address.ZipCode, Loan.Amount 
FROM 
	Client 
INNER JOIN 
	Address ON Client.AddressID=Address.ID 
INNER JOIN 
	Loan ON Client.ID=Loan.ClientID 
WHERE 
	Address.ZipCode > '3000' 
AND 
	Address.ZipCode < '4000' 
AND 
	Loan.Amount > 2500
;