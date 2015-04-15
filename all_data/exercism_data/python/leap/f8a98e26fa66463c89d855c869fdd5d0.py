"""
year module
- is_leap_year
"""


def is_leap_year(year):
    """tests for leap year of 'year', returns bool"""
    if not year % 400:
        return True
    if not year % 100:
        return False
    if not year % 4:
        return True
    return False
