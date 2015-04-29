def is_leap_year(year):
    """
    A leap year occurs
    a) on every year that is evenly divisible by 4
    b) except every year that is evenly divisible by 100
    c) unless the year is also evenly divisible by 400
    """
    if year%4 == 0: # test for rule a
        if year%100 == 0: # see if rule b applies
            if year%400 == 0: # see if rule c applies
                return True # according to rule c
            else:
                return False # according to rule b, where c does not apply
        return True # according to rule a, where b does not apply
    else:
        return False # according to rule a
