# Developer: Ephraim Kigamba
# Date Created: 9.1.2015
# Program Name: Leap Year
# Description: Reports if a year entered is a leap year or not
# Rules: A year is leap year if it is divisible by 4 and not divisible by 100 with exception any number divisble by 400


def is_leap_year(year):
    if (year%4) == 0:
        if (year%100) == 0:
            if (year%400) == 0:
                return True
            else:
                return False
        else:
            return True
    else:
        return False
