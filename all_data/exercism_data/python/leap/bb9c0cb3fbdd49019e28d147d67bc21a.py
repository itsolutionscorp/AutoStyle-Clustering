def is_leap_year(year):
    leap = 0 
    if year%4 == 0:
        leap = 1  
    if year%100 == 0:
        leap -=1
    if year%400 == 0:
        leap +=1    
    if leap >= 1:
        return True
    else:
        return False    
