import math

def is_leap_year(yr):
	a=yr/4.0
	b=yr/100.0
	c=yr/400.0
	if str(a).endswith('0'):
		if str(b).endswith('0'):
			if str(c).endswith('0'):
				return True
			else:
				return False
		else:
			return True
	else:
		return False
