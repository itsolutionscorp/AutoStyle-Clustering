#Returns true if year is a leap year, false otherwise.
def is_leap_year(year):
	if isinstance(year, int):
			#A year is a leap year if it is divisible by 4.
			if year % 4 == 0:
				#Unless it is also divisible by 100, then it is not.
				if year % 100 == 0:
					#Except if it is divisible by 400 as well, then it is a leap year.
					if year % 400 == 0:
						return True
					return False
				return True
	return False
