__author__ = 'sagos'


def is_leap_year(y):
    if y % 4 == 0 and not y % 100 == 0 or y % 400 == 0:
        return True
    else:
        return False