#
# year.py gives attributes of a year
#

def is_leap_year(y):
    if (y % 4 == 0):
        if (y % 100 == 0):
            if (y % 400 == 0):
                return True
	    else:
                return False
	else:
	    return True
    else:
        return False
    
