# A function to calculate Leap Years
# A leap year is divisble with 4, but not with 100 unless it is
# also divisble with 400

def is_leap_year(year):
	if year % 4 == 0 and year %100 != 0 or year % 400 == 0:
		return True
	else:
		return False
		


