"""
Leap year detection
"""

def is_leap_year(year):
    """
    Test if the supplied year is a leap year
    
    expect year as an integer
    """
    if not isinstance(year, int):
        raise TypeError("year must be a number")

    if year <= 0:
        raise ValueError('%d invalid year number' % year)

    if year % 400 == 0:
        return True

    if year % 4 == 0 and not year % 100 == 0:
        return True

    return False

    
