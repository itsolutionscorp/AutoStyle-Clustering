"""
    t3h2mas' very own year module!
"""

def is_leap_year(year):
    """
        is_leap_year(year) ->
            returns True if leap year and false otherwise
    """
    if not year % 4 == 0:
        return False
    if year % 100 == 0 and not year % 400 == 0:
        return False
    return True
