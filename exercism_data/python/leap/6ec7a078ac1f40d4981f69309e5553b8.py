#Function checks if year is a leap year.
def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True #year is divisible by 400
            return False    #year is evenly divisible by 100 but not 400
        return True #year is evenly divisible 4
    return False    #year not evenly divisible by 4
