def is_leap_year(year):
    return year % 4 == 0 and (year % 400 == 0 or not year % 100 == 0)
