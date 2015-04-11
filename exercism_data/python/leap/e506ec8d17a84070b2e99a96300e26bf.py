def is_leap_year(year):
    div4 = year % 4 == 0
    div100 = year % 100 == 0
    div400 = year % 400 == 0
    if div400:
        return True
    elif div100:
        return False
    elif div4:
        return True
    else:
        return False
