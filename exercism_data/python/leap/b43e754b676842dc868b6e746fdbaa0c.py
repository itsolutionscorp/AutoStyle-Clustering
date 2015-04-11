
#usage
#is_leap_year(numeric_year)

#rules for determing a leap year:
# If it is divisible by 4 = Leap Year
# and it is divisible by 400 = Leap Year
# Exception: If it is divisible by 4 & 100, but not 400, then it is not a leap year


def is_leap_year(intYear):
    intYear = int(intYear) #Treat it as an integer
    
    if (intYear % 4 == 0): 
        if ((intYear % 100 == 0) and (intYear % 400 == 0)):
            return True
        elif (intYear % 100 == 0) and (intYear % 400 != 0):
            return False
        else:
            return True
    else:
        return False
