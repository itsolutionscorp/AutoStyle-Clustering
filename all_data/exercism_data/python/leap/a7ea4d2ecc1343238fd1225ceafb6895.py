def is_leap_year(inYear):
	'''
	Takes any year and reports if given year is a leap year.
	
	Leap years are on every year that is evenly divisible by 4
	except every year that is evenly divisible by 100
	unless the year is also evenly divisible by 400
	'''
	if not inYear % 400:
		return True
	elif not inYear % 100:
		return False
	elif not inYear % 4:
		return True
	else:
		return False
