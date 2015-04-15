# Leap years fall every 4 years, but not on the century
# years divisible by 100, unless it is also divisible
# by 400. So we first test if inputYear is divisible by
# 4 but not 100. We then test if it is divisible by 4
# and also 400. In both cases it is true. Otherwise it
# is false.


def is_leap_year(inputYear):
    """Takes in a year as input and returns whether or not
       that year is a leap year
    """

    if inputYear % 4 == 0 and inputYear % 100 != 0:
        return True
    elif inputYear % 4 == 0 and inputYear % 400 == 0:
        return True
    else:
        return False
