def is_leap_year(year):
    """Determine whether the given year is a leap year.
    
    Parameters:
        year -- the year to check for leap year status
    """
    leap = False
    if not year % 4:
        if bool(year % 100) == bool(year % 400):
        # Leap years divisible by 100 are also divisible by 400
            leap = True
    return leap
