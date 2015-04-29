def is_leap_year(n):
	# its not a leap year if multiples of 100
	if (n % 100 == 0):
		#unless its multiple of 400 :-) 
		if (n % 400 == 0): 
			return True
		else:
			return False
	#if its multiple of 4
	if (n % 4 == 0):
		return True

	return False
