#
# Author: Ritwik Raghav
#
def is_leap_year(year):
    
    """Checks if given year is a leap year or not"""
    
    #if year is divisible by 100, it should be divisible by 400 for being a leap year
    if year%100==0:
        if year%400==0:
            return True
    #if year is not divisible by 100, then it should be divisible by 4 for being a leap year
    elif year%4==0:
        return True
    #if above conditions are false, the year is not a leap year
    return False
