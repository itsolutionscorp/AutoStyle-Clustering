def is_leap_year(year):
    div_by_100 = (year % 100 == 0)
    div_by_400 = (year % 400 == 0)
    if (div_by_4 and not div_by_100) or (div_by_4 and div_by_100 and div_by_400):
        return True
    return False
