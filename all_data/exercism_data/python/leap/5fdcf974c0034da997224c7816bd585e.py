def is_leap_year(year, is_leap=None):
	case1 = year % 4 == 0
	case2 = year % 100 == 0
	case3 = year % 400 == 0

	if case1 and not case2:
		is_leap = True
	elif case1 and case2 and case3:
		is_leap = True
	else:
		is_leap = False

	return is_leap
