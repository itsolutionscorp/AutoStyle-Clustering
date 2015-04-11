"""exercism.io - Python 'Leap' exercise solution."""

def is_leap_year(year):
    """Returns a boolean."""

    return year%4 == 0 and (year%100 != 0 or year%400 == 0)
