def is_divisible(dividend, divisor):
    return dividend % divisor == 0


def is_leap_year(year):
    return is_divisible(year, 400) or is_divisible(year, 4) and not is_divisible(year, 100)
