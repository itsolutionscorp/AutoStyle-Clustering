def is_leap_year(yr):
	if yr % 4 == 0:
		if yr % 100 ==0 and yr % 400 != 0:
			return False
		else:			
			return True
	else:
		return False
