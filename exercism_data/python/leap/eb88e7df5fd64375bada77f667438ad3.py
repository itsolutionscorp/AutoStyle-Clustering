"""
def is_leap_year(year):
	if ((year%4) != 0):
		return False
	elif (year%400) == 0:
		return True
	elif (year%100) == 0:
		return False
	else:
		return True
"""
def is_leap_year(year):
	return (year%4==0 and year%100!=0) or year%400==0
