def is_leap_year(year):
    """ Takes a year and reports whether it is a leap year.
    """

    return year % 4 == 0 and year % 100 != 0 or year % 400 == 0
