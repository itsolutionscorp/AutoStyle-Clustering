def is_leap_year(year):
	
	if not validate(year):
		print "Year Error: Please input a proper year"

	if year % 400 == 0:
		return True
	if year % 100 == 0:
		return False
	if year % 4 == 0:
		return True
	return False


def validate(year):
	return type(year) == int and year >= 0
