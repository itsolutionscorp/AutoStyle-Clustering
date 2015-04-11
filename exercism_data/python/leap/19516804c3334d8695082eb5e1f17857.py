def is_leap_year(year):
	leapyear= False
	if year%4==0:
		if year%100==0:
			if year%400==0:
				leapyear = True
		else:
			leapyear= True

	return leapyear
