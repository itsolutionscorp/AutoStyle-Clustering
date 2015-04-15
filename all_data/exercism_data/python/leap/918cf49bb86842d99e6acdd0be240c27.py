__author__ = 'Adam'


def is_leap_year(year):
    if year % 4 == 0:
        return not (year % 100 == 0 and year % 400 != 0)
    else:
        return False
