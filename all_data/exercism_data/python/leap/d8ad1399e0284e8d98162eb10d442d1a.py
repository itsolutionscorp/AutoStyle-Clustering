# Takes a year and returns whether it is a leap year or not

def is_leap_year(y):
    if y % 4 == 0:				# leap    
        if y % 100 == 0:		# not leap
            if y % 400 == 0:	# leap
                return True
            return False
        return True
    return False
