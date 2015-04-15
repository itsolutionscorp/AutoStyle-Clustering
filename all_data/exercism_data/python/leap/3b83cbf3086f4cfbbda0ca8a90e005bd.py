__author__ = 'Carl'

def is_leap_year(year):
    result = False
    if year % 4 == 0:
        if not year % 100 == 0:
            result = True
        elif year % 400 == 0:
            result = True
    return result
