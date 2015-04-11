#
# Skeleton file for the Python "Leap" exercise.
#
def is_leap_year(year):

    myYear = int(year)
    if myYear % 400 == 0:
        return True
    elif myYear % 100 != 0 and myYear % 4 == 0:
        return True

    return False
