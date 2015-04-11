def is_leap_year(test_year):
	if test_year % 4:
		return False
	if not test_year % 400:
		return True
	if not test_year % 100:
		return False
	if not test_year % 4:
		return True
