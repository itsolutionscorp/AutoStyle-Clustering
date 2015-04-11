# on every year that is evenly divisible by 4
#   except every year that is evenly divisible by 100
#     unless the year is also evenly divisible by 400

def is_leap_year(year):
	divisible_by_100 = year % 100 == 0
	divisible_by_400 = year % 400 == 0
	divisible_by_4 = year % 4 == 0
	is_leap = False
	if (divisible_by_4):
		is_leap = True
		if (divisible_by_100 and not divisible_by_400):
			is_leap = False
	return is_leap
