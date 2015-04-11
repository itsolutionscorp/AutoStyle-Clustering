def is_leap_year(num):
    if num % 400 == 0:
        return True
    elif num % 100 == 0:
        return False
    else:
        return not bool(num % 4)
