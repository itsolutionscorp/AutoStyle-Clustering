def is_leap_year(year):

    try:
        if year%4==0 and year%100!=0 or (year%4==0 and year%400==0):
            return True
        else:
            return False
        
    except TypeError: 
        return False