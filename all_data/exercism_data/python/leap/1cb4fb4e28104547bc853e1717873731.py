def is_leap_year(year):
	isLeapYear = False;
	if year % 4 == 0:
		isLeapYear = True;
		if year % 100 == 0:
			isLeapYear = False;
			if year % 400 == 0:
				isLeapYear = True;
	return isLeapYear;
	
