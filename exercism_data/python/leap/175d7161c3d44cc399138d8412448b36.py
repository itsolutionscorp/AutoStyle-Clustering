import sys



def is_leap_year(yr):
	if yr % 4 == 0:
		if yr % 100 == 0:
			if yr % 400 == 0:
				return True
			else:
				return False
		else:
			return True
	else:
		return False
