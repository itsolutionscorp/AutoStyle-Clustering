
def is_leap_year(y):
    return y%4 == 0 and (not y%100 == 0 or y%400 == 0)
