def is_leap_year(x):
	if x%400==0:
		return True
		
	elif x%4==0 and not x%100==0:
		return True
	else:
		return False