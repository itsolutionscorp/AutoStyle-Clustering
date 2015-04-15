def is_leap_year(year):
    return  year % 4 == 00 and year % 100 or year % 400 == 0
