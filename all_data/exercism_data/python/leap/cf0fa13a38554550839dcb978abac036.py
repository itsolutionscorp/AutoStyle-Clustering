#Tests to see if the number is a leap year

def is_leap_year(num):
#Place all conditions in if/else cases using modulo(%)
	if num%4==0 and (not num%100==0 or num%400==0):
		return True
	else:
		return False
