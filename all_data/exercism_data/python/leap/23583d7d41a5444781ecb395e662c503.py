def is_leap_year (test_year):
	return (mod(test_year,4)) and (not mod(test_year,100) or mod(test_year,400))

def mod (number, mod):
	return number % mod == 0
