def _is_divisible_by(number, divisor):
    return not number % divisor


def is_leap_year(year):
    if _is_divisible_by(year, 4):
        if _is_divisible_by(year, 100):
            if _is_divisible_by(year, 400):
                return True
            return False
        return True
    return False
