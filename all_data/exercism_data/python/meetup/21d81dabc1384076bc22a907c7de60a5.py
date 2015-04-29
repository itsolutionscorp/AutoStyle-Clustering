import calendar as cal
from datetime import date

def meetup_day(y,m,wd,meet):
    month = cal.monthcalendar(y,m)
    wds = ("Monday",'Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday')
    
    options = {'1st': num, '2nd': num, '3rd': num, '4th': num, 'teenth': teenth, 'last': last}
    return options[meet](y,m,wd,meet,month,wds)

def num(y,m,wd,meet,month,wds):
    numbs = ('1st','2nd','3rd','4th')
    if month[0][wds.index(wd)] != 0:
        return date(y,m,month[numbs.index(meet)][wds.index(wd)])
    else:
        return date(y,m,month[numbs.index(meet)+1][wds.index(wd)])

def teenth(y,m,wd,meet,month,wds):
    for week in month:
        if week[wds.index(wd)] <20 and week[wds.index(wd)] > 12:
            return date(y,m,week[wds.index(wd)])
        
def last(y,m,wd,meet,month,wds):
    if month[len(month)-1][wds.index(wd)] != 0:
        return date(y,m,month[len(month)-1][wds.index(wd)])
    else:
        return date(y,m,month[len(month)-2][wds.index(wd)])
