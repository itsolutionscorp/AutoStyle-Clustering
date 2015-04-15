"""
Determine is a specified year is a leap year
"""

def is_leap_year(year):
    """
    Input: a year as an integer. 
    Output: True if year is a leap year, False
    """
    # If divisible by 400, its a leap year
    if year % 400 == 0:
        return True
    # If devisible by 100, its not a leap year, unless also divisible by 400
    if year % 100 == 0:
        return False
    # If divisible by 4, its a leap year, unless also divisible by 100
    if year % 4 == 0: 
        return True
