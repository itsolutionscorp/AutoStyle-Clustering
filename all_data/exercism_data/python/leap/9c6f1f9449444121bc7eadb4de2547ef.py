# -*- coding: utf-8 -*-
"""Year module.
Includes a method which determines whether a given year
is a leap year, or not.

Example:
    $ from year import is_leap_year
    $ if is_leap_year(2014):
    $   print("2014 is a leap year.")
    $ else:
    $   print("2014 isn't a leap year.")
"""

def is_leap_year(year):
    """Determines if the given year is a leap year and returns the result.
    Args:
        year (int): The given year.
    Returns:
        bool: True if it's a leap year, False otherwise.    
    """
    
    # check whether or not the given year is an integer
    assert type(year) == int
    if year % 4 != 0:
        return False
    if year % 100 == 0 and year % 400 != 0:
        return False
    return True
