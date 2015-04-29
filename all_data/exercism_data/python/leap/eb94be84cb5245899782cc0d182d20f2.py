# Leap Year Test

def is_leap_year(year):
	divisble_by_four = (year % 4) == 0
	divisble_by_100 = (year % 100) == 0
	divisble_by_400 = (year % 400) == 0
	return (divisble_by_four and not divisble_by_100) \
           or (divisble_by_four and divisble_by_100 and divisble_by_400)
