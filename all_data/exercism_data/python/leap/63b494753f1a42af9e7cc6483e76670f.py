"""amyfu's solution to exercism leap"""

def is_leap_year(year):
    """
    This function takes a year as an input.
    It returns true if the year is a leap year, and false otherwise.
    """

    if year%4 == 0:
        if year%400 == 0:
            return True
        if year%100 == 0:
            return False
        return True
    return False
