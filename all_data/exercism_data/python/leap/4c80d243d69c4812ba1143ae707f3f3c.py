def is_leap_year(year):
    #We know it's not a leap year if the year is not 
    #divisible by 4...
    if year % 4 != 0:
        return False
    #...and also if the year is divisible by 100 and not divisible 
    #by 400...
    elif (year % 100 == 0) and not (year % 400 == 0):
	return False 
    #...and everything else -is- a leap year.
    return True
