def is_leap_year(year):
    """Determines if a year is a leap year"""
    if year % 4 != 0:
        return False

    if year % 100 != 0:
        return True

    if year % 400 == 0:
        return True
    
    return False
