##  Leap Year Test

def is_leap_year(year):
    ## Assume it's not a leap year
    leap = False
    
    ##    Is  year divisible by 4?  If Yes, check 100
    if int(year) % 4 == 0:
        leap = True

        ##    Is the year divisible by 100?  If Yes, it's not a leap year.
        if int(year) % 100 == 0:
            leap = False
            
    ##    Is the year divisible by 400?  If Yes, it's a leap year.
    if int(year) % 400 == 0:
        leap = True
        
    return leap
