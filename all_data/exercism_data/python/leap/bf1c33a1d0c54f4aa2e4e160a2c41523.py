def is_leap_year(input_year):
	result = False;
	if input_year % 4 == 0:
		result = True;
		if input_year % 100 == 0:
			result = False;
			if input_year % 400 == 0:
				result = True;
	return result
