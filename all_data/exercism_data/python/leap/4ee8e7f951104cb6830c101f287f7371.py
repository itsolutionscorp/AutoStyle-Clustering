## A function to determine whether a given year is a leap year ##
## written by Joseph Eschweiler 092814 ##


def is_leap_year(year):
	if year % 400 == 0: return True             ##Because if year is divisible by 400 it's also divisible by 4
	if year % 100 == 0: return False			##From the rules
	if year % 4 == 0: return True				##Because the /100's have already been eliminated	
	return False								## Because it must be divisible by 4
	

			
	
