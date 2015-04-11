def is_leap_year(year):
    """
    This function checks if the given year is a leap year
    """

    # Standard Check
    if( year % 4 == 0):
        # Make sure it doesn't fit the exception 
        if( (year % 100 == 0) and not (year % 400 == 0)):
            return False
        return True

    return False
