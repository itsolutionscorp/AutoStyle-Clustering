def is_leap_year(y):
	if (y%4==0):
		if (y%100!=0):
			return bool(1) 
		elif (y%400==0):
			return bool(1) 
	
	return bool(0) 
