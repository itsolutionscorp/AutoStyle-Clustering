def is_leap_year(input_year):
	if (input_year % 4 == 0 and input_year % 100 != 0) or input_year % 400 == 0:
		return True
	else:
		return False
