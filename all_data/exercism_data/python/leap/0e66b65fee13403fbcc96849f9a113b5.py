def is_leap_year(number):
	isleap = False
	if number % 4 == 0:
		isleap = True
	if number % 100 == 0:
		isleap = False
	if number % 400 == 0:
		isleap = True
			
	
	return isleap
