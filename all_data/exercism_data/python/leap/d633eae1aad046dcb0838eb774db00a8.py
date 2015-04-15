__author__ = 'Dalton'


def is_leap_year(year):

    if year % 4 == 0:  ##if year is divisible by 4, it's a leap year
        if year % 100 == 0: ##unless it's divisible by 100 as well
            if year % 400 == 0: ##unless it's divisible by 4, 100, and 400
                return True
            else:
                return False
        else:
            return True
