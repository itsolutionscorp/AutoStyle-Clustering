def is_leap_year(nYear):
	"""
	Returns True if year is leap year, or False if not.
	
	On every year that is evenly divisible by 4
	  except every year that is evenly divisible by 100
		unless the year is also evenly divisible by 400
	"""
	
	if nYear % 400 == 0:
		return True
	else:
		if nYear % 100 == 0:
			return False
		if nYear % 4 == 0:
			return True
	return False
