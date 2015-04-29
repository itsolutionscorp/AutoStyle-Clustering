def is_leap_year(year):
    """
    Determine if year is a leap year.

    :param year: Year to test.
    :return: True if it is a leap year.
    """

    return year % 400 == 0 or year % 4 == 0 and year % 100 != 0
