def is_leap_year(y):
	return y % 4 == 0 and (y % 100 != 0 or y % 400 == 0 )
	#return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)
