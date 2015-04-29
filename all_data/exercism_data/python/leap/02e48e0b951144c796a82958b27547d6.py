def is_leap_year(year):
	if year % 4 == 0:
		#if it's a century
		if year % 100 == 0:
			#and if the century is also divisible by 400
			#then it's a leap year
			if year % 400 == 0:
				return True
			#otherwise not a leap year
			else:
				return False
		#divisible by four so it's a leap year
		return True
