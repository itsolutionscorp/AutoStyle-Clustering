def is_divisible_by_400(year):
    return year % 400 == 0

def is_divisible_by_100(year):
    return year % 100 == 0

def is_divisible_by_4(year):
    return year % 4 == 0

def is_leap_year(year):
    if is_divisible_by_400(year):
        return True
    if is_divisible_by_100(year):
        return False
    return is_divisible_by_4(year)
