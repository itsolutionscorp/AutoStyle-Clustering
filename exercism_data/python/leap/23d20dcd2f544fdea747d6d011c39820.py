def is_leap_year(year):
    # if the year is divisible by 4, it may be a leap year
    if year % 4 == 0:

    # if divisible by 400, it is a leap year.
		if year % 400 == 0:
			return True

    # if the year is divisible by 100, it is a not a leap year
    #(we already checked for %400)
    
		elif year % 100 == 0:
			return False
	
	# if we get here, the year is divisible by 4, but not 100
	# -> leap year		
		return True

    # other years are not leap years    
    return False
