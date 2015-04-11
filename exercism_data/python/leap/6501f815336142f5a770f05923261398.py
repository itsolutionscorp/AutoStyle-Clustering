def is_leap_year(year):
	if year%400==0:
		return True
	elif (year%4==0) & (year%100>0):
		return True
	else:
		return False
