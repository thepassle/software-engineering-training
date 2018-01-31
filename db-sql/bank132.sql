-- Lijst van transacties behorende bij een betaling voor een actieve lening gegroepeerd per cliënt.

-- Standard loan payments or Code: LP nodig, 
-- DataClosed moet Null zijn
-- Groeperen per Client

SELECT AccountTransaction.Amount, AccountTransaction.Code, Loan.AccountID, Loan.DateClosed, Loan.ClientID, Client.ID, Client.FamilyName 
FROM AccountTransaction 
INNER JOIN Loan ON AccountTransaction.AccountID=Loan.AccountID 
INNER JOIN Client ON AccountTransaction.AccountID=Client.ID 
WHERE Loan.DateClosed IS NULL 
AND AccountTransaction.Code='LP' 
ORDER BY Client.ID


