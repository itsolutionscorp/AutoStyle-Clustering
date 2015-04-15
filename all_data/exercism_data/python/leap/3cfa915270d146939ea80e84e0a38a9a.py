'''
Created on Sep 24, 2014

@author: adsmith
'''
def is_leap_year(y):
    try:
        y = int(y)
    except ValueError:
        raise
    return y%400 == 0 or (y%4 == 0 and not y%100 == 0)
    # all years that are evenly divisible by 400 are leap years
    # otherwise the year must divide evenly by 4 but NOT by 100
    
    # I prefer the look of y % some_num == 0 to not y % some_num.
    # YMMV
