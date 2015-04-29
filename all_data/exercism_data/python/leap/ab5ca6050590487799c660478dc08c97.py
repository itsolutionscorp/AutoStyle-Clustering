#
# Skeleton file for the Python "Leap" exercise.
#
def is_leap_year(year):
    year = int(year)
    return ((year % 4 == 0) and (year % 100 == 0) and (year % 400 == 0)) or ((year % 4 == 0) and (not year % 100 == 0 ))
