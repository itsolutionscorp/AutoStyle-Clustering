def is_leap_year(year):
	return True if divides_evenly(year, 400) or \
				   divides_evenly(year, 4) and not divides_evenly(year, 100) else False



def divides_evenly(number, divisor):
	return number % divisor == 0
