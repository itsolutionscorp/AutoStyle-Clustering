def is_leap_year(year):
	result = False
	if((year % 4 == 0) and (not (year % 100 == 0))):
		result = True
	if(year % 400 == 0):
		result = True
	return result
