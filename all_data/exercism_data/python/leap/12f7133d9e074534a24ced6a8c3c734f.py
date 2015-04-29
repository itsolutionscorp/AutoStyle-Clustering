#!/usr/bin/python
# exercism python #2: leap

def is_leap_year(year):
    div_by_4 = year % 4 == 0
    div_by_100 = year % 100 == 0
    div_by_400 = year % 400 == 0
    
    if div_by_4:
        if div_by_100:
            if div_by_400:
                return True
            else:
                return False
        else:
            return True
    else:
        return False
