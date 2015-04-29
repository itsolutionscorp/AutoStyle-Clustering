
def is_leap_year(year):
	returnval = ""
	if (year%4==0) & (year%100!=0): 
		returnval = True
	elif (year%400==0):
		returnval = True
	else:
		returnval = False	
	return returnval
