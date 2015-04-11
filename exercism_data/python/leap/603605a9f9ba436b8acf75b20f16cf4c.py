"""
    Module to check if year is a leap year.
"""
def is_leap_year(year):
    """
        Checks if given year is a leap year.
    """
    if not isinstance(year, (int, long)):
        raise Exception("Year is not a valid integer.")
    if year % 400 == 0:
        return True
    if year % 100 == 0:
        return False
    if year % 4 == 0:
        return True
    return False
