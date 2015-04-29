def is_leap_year(x):
    if x % 4 == 0 and x % 100 == 0 and x % 400 == 0:
        return True
    elif x % 4 == 0 and x % 100 == 0:
        return False
    elif x % 4 == 0:
        return True
    else:
        return False
