def is_century(year):
	return (year % 4 == 0) and (year % 100 == 0)

def is_leap_year(year):
	if is_century(year):
		return (year % 400 == 0) == (year % 100 == 0)
	else:
		return (year % 4  == 0 or (year % 400) == (year % 100))
