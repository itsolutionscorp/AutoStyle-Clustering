def divides(a, b):
	return b % a == 0
def is_leap_year(year):
	return divides(400, year) or (divides(4, year) and not divides(100, year))
