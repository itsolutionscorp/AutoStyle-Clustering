def is_leap_year(y):
	if y % 400 == 0: return 1
	elif y % 100 == 0: return 0
	elif y % 4 == 0: return 1
	else: return 0
