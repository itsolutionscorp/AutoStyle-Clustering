def is_leap_year(y):
    if y % 100 == 0:
        return y % 400 == 0
    else:
        return y % 4 == 0
