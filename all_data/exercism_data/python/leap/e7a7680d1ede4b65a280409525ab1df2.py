# year module

def is_leap_year(year):
    """Test if the year is a leap year

    :param year: year to test
    :returns: if year is leap year
    :rtype: boolean

    """
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True
    else:
        return False
