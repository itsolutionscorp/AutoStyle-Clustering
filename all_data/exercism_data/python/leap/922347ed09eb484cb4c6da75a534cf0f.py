#leap.py

#Determine if a year is a leap year
#   every year that is evenly divisible by 4
#      except every year that is evenly divisible by 100
#         unless the year is also even divisible by 400

def is_leap_year(year) :

    if (year % 400) == 0 :
        return True
    elif (((year % 4) == 0) and
        not (year % 100) == 0) :
        return True
    else :
        return False
