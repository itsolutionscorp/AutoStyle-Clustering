def is_divisible(x, y):
    return x % y == 0

def is_leap_year(year):
    if is_divisible(year, 4):
        if is_divisible(year, 100):
            if is_divisible(year, 400):
                return True
        else:
            return True
    return False
