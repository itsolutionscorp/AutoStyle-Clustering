#
# File for the Python "Leap" exercise.
#

def is_leap_year(year):
    """ Checks whether or not the given year is leap year. """
    if year % 4 == 0:
        if year % 100 == 0 and not year % 400 == 0:
            return False
        else:
            return True
    else:
        return False
