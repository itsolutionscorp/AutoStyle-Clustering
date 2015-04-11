#
# Skeleton file for the Python "Leap" exercise.
#
def is_leap_year(year):
    year = int(year)
    if year % 400 == 0:
        return True

    if year % 100 == 0:
        return False

    return year % 4 == 0
