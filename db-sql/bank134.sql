-- De som van alle betalingen voor een actieve Loan gegroepeerd per cliënt. Deze query kan op twee manieren uitgevoerd worden, maak ze alle twee.

SELECT Client.ID, 
	SUM(CASE WHEN AccountTransaction.Description LIKE 'Standard loan payment' 
		AND Loan.ContractDate < AccountTransaction.Date 
		THEN AccountTransaction.Amount 
		ELSE 0 
		END) 
	FROM Client 
INNER JOIN Account ON Account.ClientID=Client.ID 
INNER JOIN Loan ON Loan.AccountID=Account.ID 
INNER JOIN AccountTransaction ON AccountTransaction.AccountID=Account.ID 
WHERE Loan.DateClosed IS NULL 
GROUP BY Client.ID