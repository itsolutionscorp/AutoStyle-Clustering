'''
    A leap year occurs:
        on every year that is evenly divisible by 4
        except every year that is evenly divisible by 100
        unless the year is also evenly divisible by 400
'''

def is_leap_year(year):
    
    # Determine whether the given year is a leap year.
    if year % 4 == 0 and year % 100 != 0 or year % 400 == 0:
        # Order of operations for logical operators suggests they will be
        # evaluated in the order presented. So, first we evaluate whether the
        # year is evenly divisble by 4, AND it is not evenly divisible by 100.
        # If this evaluates to True, there is no need to evaluate the part of
        # the expression following the OR. Only if the evaluation is False do we
        # proceed to evaluate the part of the expression following OR, to see
        # whether the year is evenly divisible by 400. If it is, the entire
        # expression evaluates True. Otherwise it evaluates False.
        return True
    else:
        return False
