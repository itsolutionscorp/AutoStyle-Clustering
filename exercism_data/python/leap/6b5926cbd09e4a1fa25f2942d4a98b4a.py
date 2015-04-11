# Determines if a given year is a leap year.
#
# A leap year occurs:
# on every year that is evenly divisible by 4
#   except every year that is evenly divisible by 100
#     unless the year is also evenly divisible by 400
#
# For example, 1997 is not a leap year, but 1996 is.  1900 is not a leap
# year, but 2000 is.
#


def is_leap_year(year):

    if year:
        # Is a leap year if divisible by 400
        if year % 400 == 0:
            return True

        # Not a leap year if divisible by 100
        elif year % 100 == 0:
            return False

        # Otherwise a leap year if divisible by 4
        elif year % 4 == 0:
            return True

    # If not match on anything yet, then is not a leap year
    return False
