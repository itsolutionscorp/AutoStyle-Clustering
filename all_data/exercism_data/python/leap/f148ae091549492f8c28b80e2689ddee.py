__author__ = 'Adam'


def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else:
                return False
            return year % 400 == 0
        else:
            return True
    else:
        return False
