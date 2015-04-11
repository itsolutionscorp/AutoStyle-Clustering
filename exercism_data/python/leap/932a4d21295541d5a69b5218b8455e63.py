def is_leap_year(year):
    """Checks to see if the year provided is a leap year and returns True or False"""
    ## Return true only for a year iff that year is:
    ##    1.evenly divisible by 4
    ##    2.not evenly divisible by 100
    ##    3.not evenly divisible by 400 
    if year % 4 == 0 and not (year % 100 == 0 and year % 400):
            return True
    return False
