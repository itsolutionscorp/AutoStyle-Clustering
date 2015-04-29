def is_leap_year(year):
	if divmod(year,400)[1] ==  0 or (divmod(year,4)[1] ==  0 and divmod(year,100)[1] !=  0):
		return True
	return False
