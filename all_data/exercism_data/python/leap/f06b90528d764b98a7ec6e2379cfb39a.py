def is_leap_year(year):
	#We will assume the year is not a leap year for now
	leap_year = False
	
	#First, test the 4 year divisibility
	if(year % 4 == 0):
		leap_year = True
		
		#then check for 100 year
		if(year % 100 == 0):
			leap_year = False
			
			#finally, 400 year
			if(year % 400 == 0):
				leap_year = True
	
	return leap_year
		
