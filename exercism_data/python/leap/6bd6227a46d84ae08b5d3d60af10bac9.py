def is_leap_year(y, _divides=lambda m, n: n % m == 0):
    return _divides(4, y) and (not _divides(100, y) or _divides(400, y))
