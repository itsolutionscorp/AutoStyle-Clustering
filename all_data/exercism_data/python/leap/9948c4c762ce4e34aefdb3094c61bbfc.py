
#Divisible by 4 but not 100 unless it's also divisible by 400!

def is_leap_year(year):
    if ((year % 4) != 0):
        return False            #If It's not divisible by 4, it's not a leap year.
    if ((year % 100) == 0):      
        return ((year % 400) == 0)      #If It's divisible by 100, return its divisibility by 400.
    return True #The only possibilities left unreturned are multiples of 4 which are not multiples of 100.
