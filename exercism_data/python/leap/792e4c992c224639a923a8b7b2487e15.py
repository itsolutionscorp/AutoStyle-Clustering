def is_leap_year(year):
    ''' Returns True when `year` is a leap year '''
    is_leap = False
    by_4, by_100, by_400 = [is_factor(year, factor) for factor in (4, 100, 400)]
    if by_4:
        is_leap = True
        if by_100 and (not by_400):
            is_leap = False
    return is_leap

def is_factor(operand, factor):
    ''' Is `factor` a factor of `operand`?
    Said another way, does `operand` % `factor` == 0 ?'''
    return ((operand % factor) == 0)
