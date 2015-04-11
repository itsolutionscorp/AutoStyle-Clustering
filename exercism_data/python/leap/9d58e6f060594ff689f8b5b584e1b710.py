
# on every year that is evenly divisible by 4
# except every year that is evenly divisible by 100
# unless the year is also evenly divisible by 400


def is_leap_year(year):
	#  Let's mod 400 first since if its divisible by 400,
	#  its also gotta be divisible by 4 and no nested if/else is needed now
	if year % 400 == 0: # Mod 400
		return True
	if year % 100 == 0: # Mod 100
		return False
	if year % 4 == 0: # Mod 4
		return True
	else:
		return False
