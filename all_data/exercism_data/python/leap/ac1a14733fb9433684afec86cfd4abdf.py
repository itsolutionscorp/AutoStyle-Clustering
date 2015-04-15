# determines whether or not int y represents a leap year (according to the Gregorian calendar)
def is_leap_year(y):
	return y%400==0 or ( y%4==0 and y%100!=0 )
