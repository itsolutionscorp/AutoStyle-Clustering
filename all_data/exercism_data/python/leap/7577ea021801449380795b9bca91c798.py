def is_leap_year(year):
    """return true if year is/was a leap year; false otherwise"""
    if not (year % 4) and ((year % 100) or (not (year % 100) and not (year % 400))):
	return True
    return False
