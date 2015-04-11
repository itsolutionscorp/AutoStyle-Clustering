#year.py: calculate if year is leap year

def is_leap_year(year):
    if type(year) != int:
        print 'please submit int'
        return False
    
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else: #divisible by 4 and 100, but not 400
                return False
        else: #divisible by 4, but not 100
            return True
    else: #not divisible by 4
        return False
