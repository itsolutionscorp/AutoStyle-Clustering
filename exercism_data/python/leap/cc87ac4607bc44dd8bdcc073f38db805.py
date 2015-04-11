def is_leap_year(year):
    """Tests whether or not a year is a leap year.

    Args:
        year: a year in yyyy format.
    Returns:
        True or False.

    """
    if year % 400 == 0:
        return True
    elif year % 4 == 0 and year % 100 != 0:
        return True
    else:
        return False
