def is_leap_year(year):
    """Function takes integer input and return True if it is a leap year, false otherwise.

    """
    if year%4 == 0:
        if year%100 == 0:
            if year%400 == 0:
                return True #divisible by 4, 100 and 400
            return False #divisible by 4 and 100, not by 400
        return True #simply divisible by 4 with no other conditions applying
    return False #not divisible by 4
