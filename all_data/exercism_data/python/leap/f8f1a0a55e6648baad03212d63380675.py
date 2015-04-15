def is_leap_year(y):
    if y % 4 == 0 and (y % 400 == 0 or not y % 100 == 0):
        return True
    else:
        return False
