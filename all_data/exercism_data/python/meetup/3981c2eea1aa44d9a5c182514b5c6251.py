# Meetup Python Exercism, Iteration 1

from datetime import date
import string


def first_d_after_n(y,m,d,n):
    """ Return the date of the first d (day) after or on the nth of the month """
    return ((d - date(y,m,n).weekday()) % 7) + n
        
def is_leap_year(y):
    """ Return if y is a leap year """
    if y % 4 != 0:
        return False
    else:
        return y % 400 == 0 or y % 100 != 0
          
def days_in_month(y,m):
    """ Return the number of days in the month m in the year y """
    no_of_days = {
        1: 31 ,
        2: 29 if is_leap_year(y) else 28,
        3: 31,
        4: 30,
        5: 31,
        6: 30,
        7: 31,
        8: 31,
        9: 30,
        10: 31,
        11: 30,
        12: 31
    } 
    return no_of_days[m]
            
def meetup_day_last(y,m,d):
    """ Return the date of the last day d in month m in the year y """
    max = days_in_month(y,m)
    first_d_after_28 = first_d_after_n(y,m,d,28)
    if first_d_after_28 <= max: # if the last d is the fifth d of the month
        return date(y,m,first_d_after_28)
    else: # if the last d is the fourth d of the month
        return date(y,m,first_d_after_n(y,m,d,21))
    
def meetup_day(y,m,d,which):
    # Convert the day d into a number
    days = {
        'Monday': 0,
        'Tuesday': 1,
        'Wednesday': 2,
        'Thursday': 3,
        'Friday': 4,
        'Saturday': 5,
        'Sunday' : 6
    }
    d = days[d]
    
    if which == "first":
        return date(y,m,first_d_after_n(y,m,d,1))
    elif which[0] in string.digits:
        day = first_d_after_n(y,m,d,1) + (int(which[0])-1)*7
        return date(y,m,day)
    elif which == "last":
        return meetup_day_last(y,m,d)
    elif which == "teenth":
        return date(y,m,first_d_after_n(y,m,d,13))
