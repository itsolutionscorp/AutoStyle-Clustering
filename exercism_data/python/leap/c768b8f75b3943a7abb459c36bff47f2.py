def _divisible_by(d, n):
    return n % d == 0


def is_leap_year(y):
    return _divisible_by(4, y) and (_divisible_by(400, y) or
                                    not _divisible_by(100, y))
