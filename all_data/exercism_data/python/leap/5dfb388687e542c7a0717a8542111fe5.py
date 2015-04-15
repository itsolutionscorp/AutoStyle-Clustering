def is_leap_year(year):
    def is_divisible_by(x, y):
        if x % y == 0:
            return True

    if is_divisible_by(year, 4) and not is_divisible_by(year, 100) \
            or is_divisible_by(year, 400):
        return True
    else:
        return False
