def is_leap_year(year):
    if (year%4 == 0):
        # Year is divisible by 4 => leap year
        if (year%100 == 0):
            #Year is divisible by 100 => not a leap
            if (year%400 == 0):
                #Year is divisible by 400 => exceptionnal year
                return True
            return False
        return True
    return False
