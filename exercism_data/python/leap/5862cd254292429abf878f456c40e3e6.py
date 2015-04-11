"""
Year to calc the leap year
"""
def is_leap_year(year):
    """
    Calc leap year
    """

    if year % 400 == 0:
        return True

    if year % 100 == 0:
        return False

    leap = year % 4 == 0

    return leap
# is_leap_year()
