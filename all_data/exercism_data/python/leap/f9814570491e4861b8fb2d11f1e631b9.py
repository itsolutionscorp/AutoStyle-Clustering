'''
    A leap year occurs:
        on every year that is evenly divisible by 4
        except every year that is evenly divisible by 100
        unless the year is also evenly divisible by 400
'''

def is_leap_year(year):

    # Determine whether the given year is a leap year.
    if year % 4 == 0:
        # This is a ossible leap year, because it's evenly divisible by 4.
        if year % 100 == 0:
            # The year is evenly divisible by 100, which disqualifies it.
            # Unless...
            if year % 400 == 0:
                # The year is evenly divisible by 400. If it is, return True.
                return True
            else:
                # Otherwise, return False.
                return False
        else:
            # We haven't run afoul of the 100/400 year rule, so we must be
            # a leap year. Return True.
            return True
