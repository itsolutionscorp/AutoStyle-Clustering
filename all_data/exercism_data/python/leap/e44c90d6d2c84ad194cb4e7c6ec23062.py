
def is_leap_year(year):
	
	IS_LEAP = False
	
	if(year % 4 == 0):
		IS_LEAP = True
	if(year % 100 == 0):
		IS_LEAP = False
		if(year % 400 == 0):
			IS_LEAP = True
	
	return IS_LEAP
		
	
		
		
