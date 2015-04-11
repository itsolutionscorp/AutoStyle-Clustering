__author__ = 'Cedric Zhuang'


def is_leap_year(year):
    return not (year % 4 != 0 or year % 100 == 0 and year % 400 != 0)
