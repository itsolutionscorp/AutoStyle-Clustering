def _is_divisible(number, divider):
    return number % divider == 0
def is_leap_year(year):
    return _is_divisible(year, 4) and (_is_divisible(year, 100) == _is_divisible( year, 400))
