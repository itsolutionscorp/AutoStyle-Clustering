# File: year.py
# Author: Theo Love
# Date: 2014-09-23
# Description: A solution to the leap year problem


# Returns whether or not a given year is a leap year
def is_leap_year(yr):
    # check to make sure yr is a positive integer
    if not str(yr).isdigit():
        return False
        
    # check whether yr is a leap year
    if yr % 4 == 0:
        if yr % 100 == 0:
            if yr % 400 == 0:
                return True
            else:
                return False
        else:
            return True
    else:
        return False
        
