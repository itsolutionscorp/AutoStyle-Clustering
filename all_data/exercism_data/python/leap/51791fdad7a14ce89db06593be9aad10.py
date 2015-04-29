def is_leap_year(year):
    is_divisible_by = lambda x: year % x == 0

    if is_divisible_by(400):
        return True
    if is_divisible_by(100):
        return False
    if is_divisible_by(4):
        return True
    return False
