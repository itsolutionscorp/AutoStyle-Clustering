#
# Second exercism

def is_leap_year(year):  
    if year % 4  <> 0:
        return False
    elif ((year % 100 ==0)and (year % 400 <> 0 )):
        return False
    else:
        return True

print is_leap_year(1900)
print is_leap_year(2012)
print is_leap_year(2000)
