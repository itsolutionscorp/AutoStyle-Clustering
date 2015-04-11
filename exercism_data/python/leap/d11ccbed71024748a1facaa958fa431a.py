# coding: utf-8

def is_leap_year(year):
    if year % 4:
        return False
    else:
        if year % 100:
            return True
        else:
            if year % 400:
                return False
            else:
                return True
