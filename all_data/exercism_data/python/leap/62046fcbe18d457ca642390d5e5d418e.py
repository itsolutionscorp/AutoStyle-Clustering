def is_leap_year(year):
    """
    Checks to see if the provided year is a leap year.

    Follows the algorithm found here:
    http://en.wikipedia.org/wiki/Leap_year

    :param year: The year to check
    :return: True if the year is a leap year, else False
    """
    if year % 4 != 0:
        return False
    elif year % 100 != 0:
        return True
    elif year % 400 != 0:
        return False
    return True
