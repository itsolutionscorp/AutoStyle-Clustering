"""
year - a module for determining the properties of years.
"""

def is_leap_year(year):
    """
    Determine whether a given year is a leap year.
    """
    
    # Years are only leap years if they are divisible by four, and if they also
    # happen to be divisible by one hundred, must then also be multiples of 
    # four hundred.
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else:
                return False
        else:
            return True
    else:
        return False
