__author__ = 'dwende'

def is_leap_year(year):
    if 0 != year %4:
        return False
    if 0 == year % 100:
        if 0 == year % 400:
            return True
        return False
    return True
