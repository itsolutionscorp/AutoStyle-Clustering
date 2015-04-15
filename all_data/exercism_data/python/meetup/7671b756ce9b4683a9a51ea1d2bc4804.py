from datetime import date
from calendar import monthrange

def meetup_day(year, month, weekday, order):
    iso_day_nr = {"Monday":1,"Tuesday":2,"Wednesday":3,"Thursday":4,"Friday":5,
    "Saturday":6,"Sunday":7}
    dates = []
    for i in range(1,monthrange(year,month)[1]+1):
        if date(year, month, i).isoweekday() == iso_day_nr[weekday]:
            dates.append(date(year,month,i))
        else:
            continue
        
    if order == "teenth":
        for i in dates:
            if i.day > 12 and i.day < 20:
                return i
    elif order == "1st":
        return dates[0]
    elif order == "2nd":
        return dates[1]
    elif order == "3rd":
        return dates[2]
    elif order == "4th":
        return dates[3]
    elif order == "last":
        return dates[len(dates)-1]
    else:
        return None
