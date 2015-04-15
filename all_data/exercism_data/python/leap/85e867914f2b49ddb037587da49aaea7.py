
def is_leap_year(year):
	isLeap = False
	if(year % 4 == 0):
		isLeap = True
		if(year % 100 == 0 and year % 400 != 0):
			isLeap = False

	return isLeap
