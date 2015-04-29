def is_leap_year(year):
"""
Rules for leap year basically boil down to:
    1) evenly divisible by 4 but not by 100
    2) evenly divisible by both 100 and 400
        since evenly divisible by 100 means it must be evenly divisible by 4,
        checking for 4 is unnecessary
"""    
    if (year%4 == 0 and year%100 != 0) or (year%100 == 0 and year%400 == 0):
        return True
    else:
        return False
