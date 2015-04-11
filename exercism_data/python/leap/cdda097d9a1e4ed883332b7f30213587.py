def is_leap_year(year):
    """Function for testing if a year is a leap year"""

    #If the year can be evenly divided by 100, it is NOT a leap year,
    #unless; The year is also evenly divisible by 400. Then it is a leap year.
    if (year % 100 == 0.0):
        if year % 400 == 0.0:
            return True
        else:
            return False
    #The year is evenly divisible by 4;
    elif (year % 4 == 0.0):
        return True

    else:
        return False
