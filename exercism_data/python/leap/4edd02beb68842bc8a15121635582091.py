def evenly_divisible(x, y):
    return x % y == 0


def is_leap_year(year):
    # on every year that is evenly divisible by 4
    # except every year that is evenly divisible by 100
    # unless the year is also evenly divisible by 400
    if evenly_divisible(year, 4) and not evenly_divisible(year, 100) or evenly_divisible(year, 400):
        return True
    return False
