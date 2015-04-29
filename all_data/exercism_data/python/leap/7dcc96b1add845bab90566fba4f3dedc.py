def is_leap_year(year):
	result = False

	if year % 4 == 0:
		result = True
	if year % 100 == 0:
		result = False
	if year % 4 == 0 and year % 100 == 0 and year % 400 == 0:
		result = True 
	return result
