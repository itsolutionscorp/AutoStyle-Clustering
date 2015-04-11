# implement the 1 day per 8000 year leap calendar

# determine if a given year is a leap year
def is_leap_year(the_year):
	div_4 = not (the_year % 4)
	div_100 = not (the_year % 100)
	div_400 = not (the_year % 400)
	return div_4 and (not div_100 or div_400)
