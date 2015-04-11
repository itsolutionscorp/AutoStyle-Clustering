def is_leap_year(y):
    return not (y%4 or (not y%100 and y%400))
