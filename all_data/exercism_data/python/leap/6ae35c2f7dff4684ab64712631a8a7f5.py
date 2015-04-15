def is_divisible(dividend, divisor):
    return dividend % divisor == 0


def is_leap_year(year):
    if is_divisible(year, 400):
        return True
    elif is_divisible(year, 4) and not is_divisible(year, 100):
        return True
    else:
        return False
