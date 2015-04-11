
def is_leap_year(year):

	if year%400 == 0:
		answer = True
	elif year%100 == 0:
		answer = False
	elif year%4 == 0:
		answer = True
	else:
		answer = False

	return answer
