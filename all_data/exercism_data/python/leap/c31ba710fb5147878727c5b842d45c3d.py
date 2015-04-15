# Calculate whether a year is a leap year or not.
# 
# LEAP YEARS:
# on every year that is evenly divisible by 4
#   except every year that is evenly divisible by 100
#     unless the year is also evenly divisible by 400


def is_leap_year(year):
    return (year % 4 == 0 and not
               (year % 100 == 0 and not
                year % 400 == 0))
