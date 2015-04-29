#
# Author: klemm89
#

#Conditions of a leap year, pass in a year return if true or not
def is_leap_year(year):
	#If divisible by 4
	if year % 4 == 0:
		#If divisible by 100 and 400
		if year % 100 == 0 and year % 400 == 0:
			leap = True
		#If divisible by 100	
		elif year % 100 == 0:
			leap = False
		#If divisible by 4
		else:
			leap = True
	#If not divisible by 4			
	else:
		leap = False
	return leap
