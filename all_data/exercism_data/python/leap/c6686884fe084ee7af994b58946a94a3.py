# This function is designed to receive a year and return true
# if it is a leap year.

def is_leap_year(sampleYear):
	if sampleYear % 4 == 0:
		if sampleYear % 100 == 0 and sampleYear % 400 != 0:
			return False
		else:
			return True
	else:
		return False
	
