
def byFour(year):
    if year%4 == 0:
        return True
    
def byOneHundred(year):
    if year%100 == 0:
        return True
    
def byFourHundred(year):
    if year%400 == 0:
        return True

def is_leap_year(year):
    if byFour(year) and byFourHundred(year):
        return True
    elif byFour(year) and not byOneHundred(year):
        return True
    else:
        return False
