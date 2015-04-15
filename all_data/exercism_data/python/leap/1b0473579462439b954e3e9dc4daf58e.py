"""The Leap Excercise"""

def is_leap_year(year):
    """Tests if a given year is a leap year"""
    return is_div(year, 4) and (not is_div(year, 100) or is_div(year, 400))

def is_div(m, n):
    """Tests is value m is divsible by n"""
    return m % n == 0
