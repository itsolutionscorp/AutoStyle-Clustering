def is_divisible_by(num, divisor):
    return num % divisor == 0

def is_leap_year(year):
    if is_divisible_by(year, 4):
        if is_divisible_by(year, 400) or not is_divisible_by(year, 100):
            return True
        else:
            return False
    else:
        return False
