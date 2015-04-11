# Hanley Wasington's leap python exercism iteration 2

def is_leap_year(y):
    if y % 4 != 0:
        return False
    else:
        return y % 400 == 0 or y % 100 != 0
