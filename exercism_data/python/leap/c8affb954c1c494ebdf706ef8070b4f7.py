def is_leap_year(year):
    #Take a year as input and return True/False about whether it is a leap year

    #Years divisible by 400 are leap years
    if year % 400 == 0:
        return True

    #Year divisible by 100 are not, except the case handled above
    if year % 100 == 0:
        return False

    #Years divisible by 4 are also leap years, except the case handled above
    if year % 4 == 0:
        return True

    #Any year that has not met one of the above criteria is not a leap year
    return False
