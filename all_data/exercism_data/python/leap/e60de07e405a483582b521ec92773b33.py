def is_leap_year(x):
    return x % 4 == 0 and (x % 100 != 0 or x % 400 == 0)
