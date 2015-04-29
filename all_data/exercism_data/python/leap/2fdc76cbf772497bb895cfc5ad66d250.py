def is_leap_year(i):
	j=i % 4
	k=i % 100
	x=i % 400
	if j == 0 and k != 0:
		return True
	elif j == 0 and k == 0 and x == 0:
		return True
	else:
		return False
