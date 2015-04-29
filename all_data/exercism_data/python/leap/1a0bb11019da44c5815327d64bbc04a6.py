#returns true if given date is a leap year, false otherwise

def is_leap_year(date):
	#if divisible by 400, definitely a leap year
	if date % 400 == 0: return True 
	#if divisible by 100 (and not 400), not a leap year
	elif date % 100 == 0: return False 
	#divisible by 4 and not by 100? leap year
	elif date % 4 == 0: return True
	#otherwise not a leap year 
	else : return False
