def is_leap_year(year):
    """
    Determine if year is a leap year.

    :param year: Year to test.
    :return: True if it is a leap year.
    """

    if year % 400 == 0:
        return True

    if year % 100 == 0:
        return False

    if year % 4 == 0:
        return True

    return False
