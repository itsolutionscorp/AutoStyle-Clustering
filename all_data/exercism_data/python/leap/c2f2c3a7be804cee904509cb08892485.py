def is_leap_year(year):
    """ Returns whether or not the given year is a leap year.
    :param year: Integer
    :return: Boolean
    """
    if year % 4 == 0:
        if year % 400 == 0:
            return True
        elif year % 100 == 0:
            return False
        else:
            return True
    else:
        return False
