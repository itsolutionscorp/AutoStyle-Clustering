def is_leap_year(year):
    return (is_dividable(year, 4)
            and not (is_dividable(year, 100)
            and not is_dividable(year, 400))
            )


def is_dividable(divident, divisor):
    if divisor == 0:
        return False

    return not divident % divisor
