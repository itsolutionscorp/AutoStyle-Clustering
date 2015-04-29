# Leap
#
# exercism "leap" code challenge
# Joshua Ferdaszewski
#
# Takes a year and returns whether it is a leap year or not
# 
# on every year that is evenly divisible by 4
# except every year that is evenly divisible by 100
# unless the year is also evenly divisible by 400

def is_leap_year(year):

	year = int(year)
	ly = False

	if year % 4 == 0:
		if year % 400 == 0:
			ly =  True
		elif year % 100 ==0:
			ly = False
		else:
			ly = True

	return ly
