# Leap Year Test

def is_leap_year(year):
	divisible_by_four = (year % 4) == 0
	divisible_by_100 = (year % 100) == 0
	divisible_by_400 = (year % 400) == 0
	return (divisible_by_four and not divisible_by_100) \
           or (divisible_by_four and divisible_by_100 and divisible_by_400)
