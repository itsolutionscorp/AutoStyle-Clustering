def is_leap_year(year):
	'''
	Return true if the passed-in year is a lear year or not.

	Rules:
		- If the year is divisible by 400, it's always a leap year.
		- If the year is divisible by 100, it's not a leap year.
		- If the year is divisible by 4, it IS a leap year.

	These rules are evaluated in order.
	'''
	return True  if year % 400 == 0 else \
		   False if year % 100 == 0 else \
		   True  if year % 4   == 0 else \
		   False

def _is_leap_year(leap):
	lpyr = False
	if leap % 4 == 0:
		lpyr = True
		
		if leap % 100 == 0:
			lpyr = False
				
			if leap % 400 == 0:
				lpyr = True
	return lpyr
