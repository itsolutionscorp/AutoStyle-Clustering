def is_leap_year(year):
	if not year%4:
		if not year%100:
			if not year%400:
				return True
			else:
				return False
		else:
			return True 
	else:
		return False
	
