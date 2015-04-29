def is_leap_year(y):
    '''Check if y is a leap year using the algorithm described on wikipedia
    http://en.wikipedia.org/wiki/Leap_year'''
    y = int(y)
    if (y%4 != 0): #years not divisible by 4 are always common
        return False
    elif (y%100 != 0): #non-century years are always leap 
        return True
    elif (y%400 != 0): #century years are leap only if multiples of 400
        return False
    else:
        return True
        
