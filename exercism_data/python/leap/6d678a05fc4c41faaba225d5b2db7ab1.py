"""The Leap Excercise"""

def is_leap_year(year):
    """Tests if a given year is a leap year"""

    is_div = lambda m, n: m% n == 0

    return is_div(year, 4) and (not is_div(year, 100) or is_div(year, 400))
