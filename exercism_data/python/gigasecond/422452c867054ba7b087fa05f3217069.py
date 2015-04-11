__author__ = 'tracyrohlin'
from datetime import date
from utils import is_leap_year

def add_gigasecond(num):

    gigasecond = 10**9
    date_string = str(num)
    date_split = date_string.split("-")
    date_int = [int(x) for x in date_split]

    year = date_int[0]

    year += (gigasecond // (3600*24*365)) #adding the birth year and how many years are in a gigasecond
    month = date_int[1]
    day = date_int[2]

    days_in_month = {1: 31, 2: 28, 3: 31, 4: 30, 5: 31, 6: 30,
                    7: 31, 8: 31, 9: 30, 10: 31, 11: 30, 12: 31}

    gigasecond_min_year = gigasecond - (31*(3600*24*365)) #the remainder of gigasecond minus 31 years
    days_per_giga = (gigasecond_min_year //(3600*24)) - 8 #removing the 8 extra days from 8 leap years found in time span

    d = 1
    while d <= days_per_giga:
        if not is_leap_year(year):  #not sure if there's a way to condense this part?
            if day < days_in_month[month]:
                day += 1
            else:
                if month == 12:
                    month, day = 1, 1
                    year += 1
                else:
                    month += 1
                    day = 1
        else:
            days_in_month[2] = 29
            if day < days_in_month[month]:
                day += 1
            else:
                if month == 12:
                    month, day = 1, 1
                    year += 1
                else:
                    month += 1
                    day = 1
        d += 1
    return date(year, month, day)


print add_gigasecond(date(1959, 7, 19))
