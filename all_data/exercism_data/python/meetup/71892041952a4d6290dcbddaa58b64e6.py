import calendar
import datetime
from dateutil import relativedelta


def meetup_day(year,month,targetday,targetweek):

    weekdays = dict(zip(list(calendar.day_name),range(7)))
    steps = dict(zip(['1st','2nd','3rd','4th','last'],range(7,36,7)))
    steps['teenth'] = 19
    targetday = weekdays[targetday]
    getDay = {0: relativedelta.MO(-1),
              1: relativedelta.TU(-1),
              2: relativedelta.WE(-1),
              3: relativedelta.TH(-1),
              4: relativedelta.FR(-1),
              5: relativedelta.SA(-1),
              6: relativedelta.SU(-1),}
    

    d = datetime.date(year,month,1) + relativedelta.relativedelta(day=steps[targetweek],weekday=getDay[targetday])
    return d

# def meetup_day(year,month,targetday,targetweek):

#     weekdays = dict(zip(list(calendar.day_name),range(7)))
#     targetday = weekdays[targetday]


#     if targetweek == 'last':
#         d = datetime.date(year,month+1,1) - datetime.timedelta(1)
#         return d - datetime.timedelta((d.weekday()-targetday)%7)
#     else:
#         steps = dict(zip(['1st','2nd','3rd','4th'],range(0,28,7)))
#         steps['teenth'] = 13

#         d = datetime.date(year,month,1) \
#                 + relativedelta.relativedelta(day=steps[targetweek],weekday=targetday)
#         return d
