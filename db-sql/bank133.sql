-- Check voor cliënt Tieneke Van Brabandt of het totaal van de transacties van 
-- haar rekening courant klopt met het saldo van haar rekening courant.

SELECT( SUM(CASE WHEN AccountTransaction.AccountID=6 AND AccountTransaction.Type='C' THEN AccountTransaction.Amount ELSE 0 END)-SUM(CASE WHEN AccountTransaction.AccountID=6 AND AccountTransaction.Type='D' THEN AccountTransaction.Amount ELSE 0 END) ) FROM AccountTransaction