def is_leap_year(year):
    """Determine if a year is a leap year.

    A year is a leap year:
        if it is divisible by 4,
            unless it is divisible by 100,
                but it is a leap year if it is divisible by 400.

    Examples:
    >>> is_leap_year(1996)
    True
    >>> is_leap_year(1997)
    False
    >>> is_leap_year(1900)
    False
    >>> is_leap_year(2000)
    True

    Args:
        year (int): The year.

    Returns:
        bool: True if a leap year, False otherwise.
    """

    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True
    else:
        return False
