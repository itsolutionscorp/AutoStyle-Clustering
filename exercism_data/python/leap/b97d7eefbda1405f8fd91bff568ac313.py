#
# Skeleton file for the Python "Leap" exercise.
#
def is_leap_year(year):
    year = int(year)
    if year % 400 == 0:
        return True

    if year % 100 == 0:
        return False

    if year % 4 == 0:
        return True
    else:
        return False

# def is_leap_year(year):
#     year = int(year)
#     cond_1 = (year % 4 == 0) and (year % 100 == 0) and (year % 400 == 0)
#     cond_2 = (year % 4 == 0) and (not year % 100 == 0 )
#     return cond_1 or cond_2
