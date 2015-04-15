def is_leap_year(year):
    div4 = year % 4 == 0
    div100 = year % 100 == 0
    div400 = year % 400 == 0

    if div4:
        if div100:
            return True if div400 else False
        return True
    return False
