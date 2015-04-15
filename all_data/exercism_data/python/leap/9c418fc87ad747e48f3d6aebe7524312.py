#
# Detecting leap_year
#
def is_leap_year(_year):

	if (_year%4)==0:
		if (_year%100)==0:
			if(_year%400)==0:
				return True
			else:
				return False
		else:
			return True
	else:
		return False 
