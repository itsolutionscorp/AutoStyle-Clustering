def is_leap_year(year):
    return not (year%4 or (year%400 and not year%100))
