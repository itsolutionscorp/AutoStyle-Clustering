''' Returns True/False if given year n is a leap year
'''
def is_leap_year(n):
    if n % 4 == 0:
        # If evenly divisible by 100, but not 400 (not a leap year)
        if n % 100 == 0 and n % 400 != 0:
            return False
        # Is divisible by 4
        return True
    else:
        # n is not evenly divisible by 4, therefore fails first check
        return False
