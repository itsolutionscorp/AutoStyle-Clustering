def is_leap_year(yr):
	if yr%400==0:
		return True
	elif yr%100==0:
		return False
	elif yr%4==0:
		return True
	else:
		return False
