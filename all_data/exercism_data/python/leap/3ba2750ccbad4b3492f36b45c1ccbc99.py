def is_leap_year(year):
    leap_year = 0
    leap4 = year % 4
    leap100 = year % 100
    leap400 = year % 400
    if leap4 == 0:
        leap_year = 1
        if leap100 == 0:
            leap_year = 0
            if leap400 == 0:
                leap_year = 1
    return leap_year
