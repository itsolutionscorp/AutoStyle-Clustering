def _is_divisible(number, divider):
    return not(bool(number % divider))
def is_leap_year(year):
    return _is_divisible(year, 4) and (_is_divisible(year, 100) == _is_divisible( year, 400))
