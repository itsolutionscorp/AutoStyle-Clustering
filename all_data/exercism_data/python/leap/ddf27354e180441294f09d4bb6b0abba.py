def is_leap_year(leap):
	if leap % 4 is 0:
		lpyr = True
		
	elif leap % 100 is 0:
		lpyr = False
				
	elif leap % 400 is 0:
		lpyr = True
	return lpyr
