def is_leap_year(n):
	if (n%4)==0 and (not (n%100)==0 or (n%400)==0): 
		return True
