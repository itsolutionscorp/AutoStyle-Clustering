def is_leap_year(year):
    return (is_dividable(year, 4)
            and not (is_dividable(year, 100) and not is_dividable(year, 400))
            )


def is_dividable(divident, divisor):
    return not divident % divisor
