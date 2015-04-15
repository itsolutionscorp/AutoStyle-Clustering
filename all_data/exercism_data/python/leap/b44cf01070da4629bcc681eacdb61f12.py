
def is_leap_year(y):
    # Years divisible by 400 are leap years
    if y % 400 == 0:
        return True
    # Years divisible by 100 are not
    elif y % 100 == 0:
        return False
    # All other years divisible by 4 are leap years
    else:
        return y % 4 == 0
