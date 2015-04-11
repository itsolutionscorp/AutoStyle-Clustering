def is_leap_year(year):
    """

    :param year:
    """

    if year % 4:  # Will be non-zero if not divisible by 4
        return False
    if not year % 100:
        if not year % 400:
            return True
        return False
    return True
