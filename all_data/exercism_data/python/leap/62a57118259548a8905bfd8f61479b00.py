def is_leap_year(year):
    # True if evenly divisible by 400
    if year % 400 == 0:
        return True
    # False evenly divisible by 100
    elif year % 100 == 0:
        return False
    # True on every year that is evenly divisible by 4
    elif year % 4 == 0:
        return True
    # Every other year is False
    else:
        return False
