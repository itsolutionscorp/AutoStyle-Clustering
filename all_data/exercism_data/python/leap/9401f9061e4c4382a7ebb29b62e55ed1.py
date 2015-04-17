# -*- coding: utf-8 -*-

def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else:
                return False
        else:
            return True
    else:
        return False

'''
for i in [1999, 2000, 1900]:
    print (i, leap(i))

'''