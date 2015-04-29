def is_leap_year(arg):
	if arg%100 == 0 and arg%400 != 0 or arg%4 != 0:
		return False
	return True
