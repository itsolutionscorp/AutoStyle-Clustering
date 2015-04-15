def is_leap_year(input):

    #if divisible by 100
    if (input % 100 == 0):
        #and divisible by 400, it is a leap year
        if (input % 400 == 0):
            return True
        return False
    
    #all other instances divisible by 4, it is a leap year
    if (input % 4 == 0):	
        return True
    return False
