from datetime import date, timedelta

def meetup_day(year, month, day, qual):
    cal_dict = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, \
      'Friday': 4, 'Saturday': 5, 'Sunday': 6}
    qual_dict = {'1st': 1, '2nd': 2, '3rd': 3, '4th': 4, 'last': 0, \
      'teenth': -1}
    iday = cal_dict[day]
    iqual = qual_dict[qual]
    if(iqual>0):
        rdate = date(year,month,1)
        daydelta = iday - rdate.weekday()
        if(daydelta < 0):
            daydelta+=7
        rdate = rdate + timedelta(days=daydelta)
        daydelta = 7*(iqual - 1)
        rdate = rdate + timedelta(days=daydelta)
    if(iqual==0):
        rdate = date(year,month+1,1)
        daydelta = iday - rdate.weekday()
        if(daydelta > 0):
            daydelta+=-7
        rdate = rdate + timedelta(days=daydelta)
    if(iqual==-1):
        rdate = date(year,month,13)
        daydelta = iday - rdate.weekday()
        if(daydelta < 0):
            daydelta+=7
        rdate = rdate + timedelta(days=daydelta)
    return rdate
