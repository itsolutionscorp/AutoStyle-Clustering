def is_leap_year(year):
	return _is_divisible_by(year, 400) or \
	(_is_divisible_by(year, 4) and \
		not _is_divisible_by(year, 100))

def _is_divisible_by(number, divisor):
	return number % divisor == 0
