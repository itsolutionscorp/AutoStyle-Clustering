def is_leap_year(y):
	return (
		(y % 400 is 0 # True if 2000
			or y % 100 is not 0) # But 1900, 1700.. doesnt count
		and y % 4 is 0 # True for anything else divisible by 4
	)
