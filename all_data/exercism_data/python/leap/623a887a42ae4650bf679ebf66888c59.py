def is_leap_year(y):
    return (y % 400 == 0) or ((y % 4 == 0) and (y % 100 != 0))
