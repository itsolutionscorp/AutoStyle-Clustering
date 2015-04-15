#Tests to see if the number is a leap year

def is_leap_year(num):
#Place all conditions in if/else cases using modulo(%)
	if num%4==0:
		if num%100==0:
			if num%400==0:
				return True
			else:
				return False
		else:
			return True
	else:
		return False
