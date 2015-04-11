def is_divisible_by_100(num):
	return (num % 100) == 0

def is_divisible_by_400(num):
	return (num % 400) == 0

def is_divisible_by_4(num):
	return (num % 4) == 0

def is_leap_year(year):
	if is_divisible_by_4(year):
		if is_divisible_by_100(year):
			if is_divisible_by_400(year):
				return True
			else:
				return False
		else:
			return True
	return False
