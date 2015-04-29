def is_leap_year(year):
	
	####Tests Year to determine if its evenly divisible by 4 and not 100 
	if year%4==0 and year%100!=0:
		return True
	
	####Tests Year to determine if its evenly divisible by 100 and also 400		
	elif year%100==0 and year%400==0:
		return True
	
	####If both cases fail, return False	
	else:
		return False
		
	
