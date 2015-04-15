import calendar
from datetime import date as d

def meetup_day(year,month,day,type):
    c = calendar.Calendar()
    days = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}
    dates = []
    if day in days:
        day_int = days[day]
    for (date,day) in c.itermonthdays2(year,month):
        if type == 'teenth':
            if day == day_int:
                if 13 <= date and 19 >= date:
                    return_date = date
        if type == '1st':
            if day == day_int:
                if 1 <= date and 7 >= date:
                    return_date = date
        if type == '2nd':
            if day == day_int:
                if 8 <= date and 14 >= date:
                    return_date = date
        if type == '3rd':
            if day == day_int:
                if 15 <= date and 21 >= date:
                    return_date = date
        if type == '4th':
            if day == day_int:
                if 22 <= date and 28 >= date:
                    return_date = date
        if type == '5th':
            if day == day_int:
                if 29 <= date and 31 >= date:
                    return_date = date
        if type == 'last':
            if day == day_int:
                dates.append(date)
    if type == 'last':
        return_date = dates[len(dates) - 1]
    return d(year,month,return_date)
