# year.py

def is_leap_year(year):
    """ Assumes 'year' is a, well, a year....in int form.
        Returns True if it is a leap year and False if it is not.
    """
    return year % 4 is 0 and not year % 100 is 0 or year % 400 is 0
