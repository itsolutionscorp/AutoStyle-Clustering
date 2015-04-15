#
# Calculate if a year is leap year
# Definition of a leap year:
#   every year that is evenly divisible by 4
#       except every year that is evenly divisible by 100
#           unless the year is also evenly divisible by 400
#

def is_leap_year(year):

    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            return False
        return True
    return False
