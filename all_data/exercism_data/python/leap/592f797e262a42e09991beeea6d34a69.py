def is_leap_year(year):
    """Checks if year is a leap year or not
    Args:
        year (int): Year to Check

    Returns:
        bool: True if the supplied year is a leap year
    """
    if year % 4:
        return False
    if not year % 100 and year % 400:
        return False
    return True
