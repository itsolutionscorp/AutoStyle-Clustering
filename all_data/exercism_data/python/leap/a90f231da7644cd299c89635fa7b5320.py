def is_leap_year(year):
	'''
	Function to test if input is a leap year.
	Returns True if leap year, else False.
	'''

	# leap year must be divisible by 4
	# but not 100, unless also divisible by 400
	return year%4 == 0 and not (year%100 == 0 and year%400 != 0)	
