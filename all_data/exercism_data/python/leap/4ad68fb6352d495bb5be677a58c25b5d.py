def is_leap_year(year):
    """Determines if a given year is a leap year"""
    if year % 400 == 0:
        return True
    elif year % 4 == 0 and year % 100 != 0:
        return True
    else:
        return False
