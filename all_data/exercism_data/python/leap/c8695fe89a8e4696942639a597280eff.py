def is_leap_year(year):
    return not bool(year % 4) and bool(year % 100) or not bool(year % 400)
