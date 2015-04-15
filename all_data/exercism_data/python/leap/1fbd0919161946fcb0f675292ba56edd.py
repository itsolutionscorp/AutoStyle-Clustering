
def is_leap_year(year):

	if not year % 4: 
		if year % 100 or not year % 400 :
			return True
	
	return False
