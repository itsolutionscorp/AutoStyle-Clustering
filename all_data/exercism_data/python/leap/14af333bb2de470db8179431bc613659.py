"""
year module

"""

def is_leap_year(year):
    """
    Tests if year is a leap year or not

    :param year:
    :return:
    """
    if 0 == year % 400:
        return True
    elif 0 == year % 100:
        return False
    elif 0 == year % 4:
        return True
    else:
        return False
