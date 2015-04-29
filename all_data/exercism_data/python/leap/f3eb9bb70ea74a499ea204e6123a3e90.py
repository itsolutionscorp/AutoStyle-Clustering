def is_leap_year(n):
    if (n % 100 != 0) and (n % 4 == 0) or (n % 400 == 0):
        return True
    else:
        return False
