# This code takes a year and determines if it is a leap year or not
# --------------------------------------------------------
# on every year that is evenly divisible by 4
# except every year that is evenly divisible by 100
# unless the year is also evenly divisible by 400
# --------------------------------------------------------

def is_leap_year(year):
	# Test if it's diviible by 4
	by_four = year % 4
	by_hundred = year % 100
	by_four_hundred = year % 400

	leap_year = False

	if by_four == 0:
		leap_year = True
		if by_hundred == 0:
			leap_year = False
			if by_four_hundred == 0:
				leap_year = True

	return leap_year
