#!/usr/bin/env python
""" exercism.io leap application.
"""

def is_leap_year(year):
    """ Determines if year submitted is a leap year.
        Returns True if it is, False otherwise
    """

    def is_divisible_by(divisor):
        """ Determines if year is divisible by the number in question.
            Returns True if it is, False otherwise
        """
        return year % divisor == 0

    # if the year is divisible by 400 it is always a leap year
    if is_divisible_by(400):
        return True
    # if the year is divisible by 4 and not 100 it is a leap year
    if is_divisible_by(4) and not is_divisible_by(100):
        return True

    return False
        
