def is_leap_year(year):
    leap_year = False
    if (not year % 4 and year % 100) or (not year % 400):
        leap_year = True
    return leap_year
