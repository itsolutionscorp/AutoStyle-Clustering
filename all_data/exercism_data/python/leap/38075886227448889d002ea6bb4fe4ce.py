import calendar
from datetime import datetime

def _looks_like_year(year):
    """
    Test to see if the year is 4 digits.
    """
    try:
        # if datetime can parse it, I think it's good enough.
        datetime(year, 1, 1) # First of january
        return True
    except:
        return False

def is_leap_year(year=None):
    """
    Function to test if a year is a leap year.

    Returns True or False depending on whether the year is a leap
    year or not.
    """
    # This is input validateion to ensure we're actually dealing with something
    # that looks like a year.
    assert _looks_like_year(year), "This does not look like a year: %s" % year
    # calendar provides a handy builtin to test if a year is a leap year
    return calendar.isleap(year)
