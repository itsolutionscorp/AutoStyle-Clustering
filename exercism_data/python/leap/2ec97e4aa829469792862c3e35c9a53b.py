##first we check to see if the year is divisable by 4
##if it is then we check to see if is divisable by 100 if not then true
##if it is divisable by a 100 then we check to see if it is divisable by 400
##if it is then true if not then it is false
def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else:
                return False
        else:
            return True
    else:
        return False
