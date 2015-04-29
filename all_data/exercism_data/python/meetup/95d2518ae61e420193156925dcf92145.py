from datetime import date
import calendar

def meetup_day(year,month,day,qualifier):
    cal = calendar.Calendar(0)
    days = ["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"]
    month_days = list((x for x in cal.itermonthdays2(year,month) if x[0] !=0 and x[1] == days.index(day)))
    
    if qualifier == "1st":
        result = month_days[0][0]
    elif qualifier == "2nd":
        result = month_days[1][0]
    elif qualifier == "3rd":
        result = month_days[2][0]
    elif qualifier == "4th":
        result = month_days[3][0]
    elif qualifier == "last":
        result = month_days[-1][0]
    elif qualifier == "teenth":
        for d in month_days:
            if d[0] in range(13,20):
                result = d[0]
    return date(year,month,result)
