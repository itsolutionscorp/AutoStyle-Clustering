def is_leap_year(year):
    return not bool(year % (4 if year % 25 else 16))
