from __future__ import unicode_literals

def is_leap_year(inputvar):
	if inputvar%400==0:
		return True
	elif inputvar%100==0:
		return False
	elif inputvar%4==0:
		return True
	else:
		return False
