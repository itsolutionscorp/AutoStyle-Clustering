'''
Leap you calculator
'''

def is_leap_year(year):
    '''
    Takes a year, and return 'True' if it is a leapyear.
    '''
    if year%400 is 0:
        return True
    elif year%100 is 0:
        return False
    elif year%4 is 0:
        return True
    else:
        return False

        
