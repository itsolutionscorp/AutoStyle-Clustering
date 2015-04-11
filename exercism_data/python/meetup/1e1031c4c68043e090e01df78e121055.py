#!/usr/bin/env python

from datetime import date

wd = {"Monday" : 0,
"Tuesday" : 1,
"Wednesday" : 2,
"Thursday" : 3,
"Friday" : 4,
"Saturday" : 5,
"Sunday" : 6}

def meetup_day(year, month, day_of_week, day):
    if day == "teenth":
        for d in range(13, 20):
            if date(year, month, d).weekday() == wd[day_of_week]:
                return date(year, month, d)
    elif day == "last":
        for d in range(31, 21, -1):
            try:
                if date(year, month, d).weekday() == wd[day_of_week]:
                    return date(year, month, d)
            except ValueError:
                pass
    elif day == "1st":
        for d in range(1, 8):
            if date(year, month, d).weekday() == wd[day_of_week]:
                return date(year, month, d)
    elif day == "2nd":
        for d in range(8, 15):
            if date(year, month, d).weekday() == wd[day_of_week]:
                return date(year, month, d)
    elif day == "3rd":
        for d in range(15, 22):
            if date(year, month, d).weekday() == wd[day_of_week]:
                return date(year, month, d)
    elif day == "4th":
        for d in range(22, 29):
            if date(year, month, d).weekday() == wd[day_of_week]:
                return date(year, month, d)
    else:
        pass
