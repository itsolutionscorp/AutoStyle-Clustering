def leapyear(year):
	if (year/4==year/4.0 and year/100!=year/100.0) or (year/4==year/4.0 and year/400==year/400.0): return True
	else: return False
