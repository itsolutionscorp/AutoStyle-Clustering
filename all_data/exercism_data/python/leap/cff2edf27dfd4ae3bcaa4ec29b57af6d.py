# year.py

def is_leap_year(year):
    """ Checks if 'year' is a leap year """
    # Check if it is a multiple of 400 first
    if not year % 400:
        return True

    # Then check if it is a multiple of 100
    if not year % 100:
        return False

    # Finally check if it is a multiple of 4
    if not year % 4:
        return True
