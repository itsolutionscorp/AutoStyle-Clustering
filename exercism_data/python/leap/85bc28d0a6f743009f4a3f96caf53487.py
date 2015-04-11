def is_leap_year(whichYear):
	leap = False
	if not whichYear % 4:
		leap = True
		if not whichYear % 100:
			leap = False
			if not whichYear % 400:
				leap = True
			else:
				leap = False
	return leap
