def is_leap_year(n):
	if (n % 100 == 0):
		if (n % 400 == 0): 
			return True
		else:
			return False
	
	if (n % 4 == 0):
		return True

	return False
