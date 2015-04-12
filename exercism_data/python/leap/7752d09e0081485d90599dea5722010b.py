def is_leap_year(year):

	if type(year) != int: return "Not a year."

	return (year % 4 == 0 and year % 100 != 0) or year % 400 == 0
		