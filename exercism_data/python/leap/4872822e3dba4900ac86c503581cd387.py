def is_leap_year(year):
	"""
	Inputs:
		year (int) - The year to check
	Output:
		cond (Logical) - True if year is a leap year
	
	Checks to see if the input year is a leap year or not.
	"""
	if (type(year) != int):
		print 'Please enter and integer.'
	else:
	
		if (year % 4 == 0):
			if (year % 100 == 0):
				if (year % 400 == 0):
					cond = True
				else:
					cond = False
			else:
				cond = True
		else:
			cond = False
		return cond
	
