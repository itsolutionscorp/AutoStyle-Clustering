def is_leap_year (year):
    
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 != 0:
                return False
    else:
        return False

    return True
