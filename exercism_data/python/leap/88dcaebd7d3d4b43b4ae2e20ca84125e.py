#leap

#this could be one line probably?

def is_leap_year(input):
	if (input % 4 == 0 and input % 100 != 0) or input % 400 == 0:
		return True
	return False
