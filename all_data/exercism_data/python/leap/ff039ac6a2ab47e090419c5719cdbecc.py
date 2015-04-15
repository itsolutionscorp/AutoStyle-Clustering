
def is_divisible(n, d):
    return n%d == 0

def is_leap_year(year):
    if is_divisible(year, 400):
        return True
    elif is_divisible(year, 100):
        return False
    else:
        return is_divisible(year, 4)
