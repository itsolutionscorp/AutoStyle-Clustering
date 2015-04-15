def is_leap_year(year):
    return not year % (year % 25 and 4 or 16)
