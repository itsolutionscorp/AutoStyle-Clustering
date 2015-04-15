def is_leap_year(year):
	if year % 4:
		return False # not 4 
	
	if year % 100: # 4, but not 100, so a leap year
		return True 
		
	if year % 400: # 100, but not 400, so not a leap year
		return False
	
	# 100 and 400, so a leap year
	return True 
	
