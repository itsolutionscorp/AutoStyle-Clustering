from datetime import date
import calendar
       
def meetup_day(y,m,w,meet):
    
    cal = calendar.Calendar()
    
    mdays = []
    for d in cal.itermonthdates(y,m):
        if d.month == m and d.strftime("%A") == w:
            mdays.append(d)

    if meet == 'teenth':
        for d in mdays:
            if 12 < d.day < 20:
                return date(y,m,d.day)
    elif meet == '1st':
        return date(y,m,mdays[0].day)
    elif meet == '2nd':
        return date(y,m,mdays[1].day)
    elif meet == '3rd':
        return date(y,m,mdays[2].day)
    elif meet == '4th':
        return date(y,m,mdays[3].day)
    elif meet == 'last':
        return date(y,m,mdays[-1].day)

    return
