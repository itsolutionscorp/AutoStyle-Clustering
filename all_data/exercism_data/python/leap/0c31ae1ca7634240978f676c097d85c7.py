def is_leap_year(year):
	bool = False
	if year % 4 == 0:
		if year % 100 == 0:
			if year % 400 == 0:
				bool = True
		else:
			bool = True
	return bool 
