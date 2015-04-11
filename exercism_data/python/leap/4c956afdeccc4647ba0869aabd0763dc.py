def leap(year):
	""" returns if year is a leap year"""
	if year%400:
		TRUE
	elif year%100:
		FALSE
	elif year%4:
		TRUE
	else:
		FALSE
