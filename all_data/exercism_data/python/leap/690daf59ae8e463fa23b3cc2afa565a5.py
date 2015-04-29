def is_leap_year(arg):
	if arg%100 == 0 and arg%400 != 0:
		return False
	elif arg%4 == 0:
		return True
	return False	
