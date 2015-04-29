import math

def is_leap_year(yr):
	a=yr%4.0
	b=yr%100.0
	c=yr%400.0
	if a==0:
		if b==0:
			if c==0:
				return True
			else:
				return False
		else:
			return True
	else:
		return False
