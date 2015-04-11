def is_leap_year(year):

    if not year % 100 == 0: # If the year is not divisible by 100, it must be a leap year if it is divisible by 4.
        if year % 4 == 0:
            return True


    if year % 400 == 0: # If the year is divisible by 400 it must be a leap year
        return True
