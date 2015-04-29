def is_leap_year(year):
    """Determine whether the given year is a leap year.
    
    Parameters:
        year -- the year to check for leap year status
    """
    # Leap years are divisible by 4 and either:
	# divisible by BOTH 100 and 400, or
	# divisible by NEITHER 100 nor 400
    return year % 4 == 0 and bool(year % 100) == bool(year % 400)
