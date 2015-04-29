#Test whether or not the varible 'year' is divisible evenly to determine wether it is a leap year or not
#This problem was helped solved using this answer from stack overflow http://stackoverflow.com/a/8002234

def is_leap_year(year):

	if year % 4 == 0:		
		if year % 100 == 0:
			if year % 400 == 0:
				return True
			else:	
				return False
		else:
			return True
	else:
		False	
