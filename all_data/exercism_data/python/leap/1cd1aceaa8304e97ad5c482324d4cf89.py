def is_leap_year(year):

# (% - Divides left hand by right hand and returns remainder)

	return (year % 4 == 0) and ((year % 100 != 0) or (year % 400 ==0))
