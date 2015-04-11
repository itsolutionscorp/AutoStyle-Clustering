def is_leap_year(year : int) -> bool:
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False

    return year % 4 == 0
