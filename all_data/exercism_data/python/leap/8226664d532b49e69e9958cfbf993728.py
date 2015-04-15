# -*- coding: utf-8 -*-

def is_leap_year(year):
    # Brackets for readability (personal opinion)
    # Checks if year is evenly divisible by 4
    if (year % 4 == 0):
        # If divisible by 400 as well, then it is a leap year
        if (year % 400 == 0):
            return True

        # If divisible by 100 and not 400, not a leap year
        if (year % 100 == 0):
            return False

        # Since we've reached this far, every remaining year
        # divisible by 4 is a leap year
        return True
