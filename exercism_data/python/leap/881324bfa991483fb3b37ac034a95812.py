def is_divisible(x, y):
    return x % y == 0


def is_leap_year(year):
    if is_divisible(year, 400):
        return True
    elif is_divisible(year, 100):
        return False
    elif is_divisible(year, 4):
        return True
