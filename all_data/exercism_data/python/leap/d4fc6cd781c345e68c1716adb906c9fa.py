def is_leap_year(year):
    if not year % 4:    # Got to use modulo to test if divisible
        if not year % 400:
            return True
        elif not year % 100:
            return False
        else:
            return True
    else:
        return False
    return
