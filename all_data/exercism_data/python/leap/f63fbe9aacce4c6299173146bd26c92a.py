'''
Leap Year Code
Brian Held
'''

def is_leap_year(year):
 #make sure input is a number
 if year%year != 0:
    return "Not a number!"
 #start with weirdest case
 elif year%400 == 0:
    return True
 #then next weirdest case
 elif year%100 == 0:
    return False
 #now the standard leap year case
 elif year%4 == 0:
    return True
 #everything else is not a leap year
 else:
    return False
