#
# Returns whether a given year is a leap year.
# Leap years occur:
# on every year that is evenly divisible by 4
#   except every year that is evenly divisible by 100
#     unless the year is also evenly divisible by 400


def is_leap_year(year):
    if year % 100 == 0:
        return year % 400 == 0
    else:
        return year % 4 == 0
