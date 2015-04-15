def is_leap_year(number):
    if number % 4 != 0:
	return 0
    elif number % 100 != 0:
	return 1
    elif number % 400 == 0:
	return 1
    return
