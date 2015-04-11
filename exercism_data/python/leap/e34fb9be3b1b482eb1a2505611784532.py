#
#   Function for the leap_test.py
#


def is_leap_year(year):
    return (year % 4 == 0 and not year % 100 == 0) or (year % 400 == 0)
