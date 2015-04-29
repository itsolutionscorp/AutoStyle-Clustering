def is_leap_year(input):
	return input % 4 == 0 and (input % 100 != 0 or input % 400 == 0)
