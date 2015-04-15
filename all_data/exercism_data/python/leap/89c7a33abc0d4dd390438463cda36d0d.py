def is_leap_year(year):
    if is_divisible(year, 4):
        if is_century(year):
            return is_leap_century(year)
        else:
            return True
    return False


def is_divisible(a, b):
    return a % b == 0

def is_century(year):
    return is_divisible(year, 100)

def is_leap_century(year):
    return is_divisible(year, 400)
