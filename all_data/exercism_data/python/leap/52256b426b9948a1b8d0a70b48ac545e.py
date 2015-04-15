def is_leap_year(year):
	""" returns if year is a leap year"""
	if year%400==0:
		return True
	elif year%100==0:
		return False
	elif year%4==0:
		return True
	else:
		return False
	
