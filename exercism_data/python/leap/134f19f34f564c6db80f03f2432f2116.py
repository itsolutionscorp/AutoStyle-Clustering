def is_leap_year(year):
    
    return divisible(year,4) if not divisible(year,100) else divisible(year,400) 

def divisible(x,y):
    '''Returns True if x is divisible by y, False otherwise.'''

    return not x % y
