def is_leap_year(year):
    if int(year) % 4 == 0:
        if int(year) % 100 == 0:
            if int(year) % 400 == 0:
                return True
            else:
                return False
        return True
    else:
        return False
