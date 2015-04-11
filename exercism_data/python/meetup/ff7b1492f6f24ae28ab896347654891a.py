from datetime import date
from datetime import timedelta
from datetime import datetime


def meetup_day(year,month,wday,n):

    wd={'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}
    d = date(year,month,1)

    if n == 'teenth':
        d = d+timedelta(days=12)
        while d.weekday() != wd[wday]:
            d = d+timedelta(days=1)
        return d
        
    if n != 'last':
        diff = wd[wday] - d.weekday()
        if diff >= 0:
            return d+timedelta(days=diff)+timedelta(weeks=int(n[0])-1)
        if diff < 0:
            return d-timedelta(days=abs(diff))+timedelta(weeks=int(n[0]))

    if n == 'last':
        d = date(year,month+1,1)-timedelta(days=1)
        while d.weekday() != wd[wday]:
            d = d-timedelta(days=1)
        return d


    return d
