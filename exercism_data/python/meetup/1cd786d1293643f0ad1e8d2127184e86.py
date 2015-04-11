import calendar
import datetime
from dateutil import relativedelta


def meetup_day(year,month,targetday,targetweek):

    weekdays = dict(zip(list(calendar.day_name),range(7)))
    steps = dict(zip(['1st','2nd','3rd','4th','last'],range(7,36,7)))
    steps['teenth'] = 19
    targetday = weekdays[targetday]
    getDay = {v: 'relativedelta.{}(-1)'.format(k[:2].upper()) for k,v in weekdays.iteritems()}
    


    d = datetime.date(year,month,1) + relativedelta.relativedelta(day=steps[targetweek],weekday=eval(getDay[targetday]))
    return d
