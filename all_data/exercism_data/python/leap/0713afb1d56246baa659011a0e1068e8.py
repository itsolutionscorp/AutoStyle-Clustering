#def is_leap_year(year):
#    y = year % 4
#    if y == 0:
    
  #      return True
    
def is_leap_year(year):
    if year % 4 == 0 and year % 100 != 0 or year % 400 == 0 : 
        return True
    else: return False
