def is_leap_year(year):
    return bool(not year % 4 and (not year % 400 or year % 100))
