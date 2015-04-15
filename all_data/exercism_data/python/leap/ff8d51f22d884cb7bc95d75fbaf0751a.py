__author__ = 'Chris'


def is_leap_year(year):
    if year % 4 and year % 100 and year % 400:
        return False
    else:
        return True
