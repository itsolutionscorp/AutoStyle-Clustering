def is_leap_year(year):
    if year%100 == 0:
        year //= 100
    return year%4 == 0
