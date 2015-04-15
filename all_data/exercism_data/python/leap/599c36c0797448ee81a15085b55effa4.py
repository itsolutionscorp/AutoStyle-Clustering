# Returns True if year is evenly divisible by 4
# except if year is evenly divisible by 100
# unless year is also evenly divisible by 400
def is_leap_year(year):
	if (year % 4 == 0) and (not year % 100 == 0 or year % 400 == 0):
		return True
