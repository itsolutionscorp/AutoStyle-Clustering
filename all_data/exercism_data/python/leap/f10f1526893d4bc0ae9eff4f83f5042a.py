def div4(year):
	if year % 4 == 0:
		return True
	else:
		return False

def div100(year):
	if year % 100 == 0:
		return True
	else:
		return False

def div400(year):
	if year % 400 == 0:
		return True
	else:
		return False

def is_leap_year(year):
	if div4(year) == True & div100(year) == True:
		if div400(year) is True:
			return True
		else:
			return False
	elif div4(year) is True:		
		return True
	
	else:
		return False
