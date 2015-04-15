__author__ = 'romleinster'


def is_leap_year(input_year):
    if input_year % 4 == 0:
        if input_year % 100 == 0:
            if input_year % 400 == 0:
                return True
            else:
                return False
        return True
    else:
        return False
