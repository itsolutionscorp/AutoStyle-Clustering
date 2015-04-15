def is_leap_year(year):
    def is_divisible_by(x, y):
        return x % y == 0

    return (is_divisible_by(year, 4) and not is_divisible_by(year, 100)) \
            or is_divisible_by(year, 400)
