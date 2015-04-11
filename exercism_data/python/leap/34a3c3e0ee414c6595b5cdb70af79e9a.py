def is_leap_year(year):
	x = 0
	if year%4 == 0:
		x += 1
	if year%100 == 0:
		x -= 1
		if year%400 == 0:
			x+=1
	if x ==1:
		return True
	else:
		return False
