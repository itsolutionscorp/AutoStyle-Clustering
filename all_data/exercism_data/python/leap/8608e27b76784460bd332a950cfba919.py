def is_leap_year(year):
	if not (year % 4 == 0):
		return False 
	if not (year % 100 == 0):
		return True 
	if not (year % 400 == 0):
		return False 
	else:
		return True 
