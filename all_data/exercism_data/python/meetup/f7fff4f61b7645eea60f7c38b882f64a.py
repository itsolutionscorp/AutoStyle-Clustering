import calendar
from datetime import date
def meetup_day(year, month, wday, series):
    wd = {'Monday' : 0,
          'Tuesday' : 1,
          'Wednesday' : 2,
          'Thursday' : 3,
          'Friday' : 4,
          'Saturday' : 5,
          'Sunday' : 6
         }
    calendar.setfirstweekday(wd[wday])
    m = calendar.monthcalendar(year, month)
    try:
        s = int(series[0:1])
        if m[0][0] == 0:
            day = m[s][0]
        else:
            day = m[s-1][0]
    except:
        if series == 'last':
            day = m[-1][0]
        if series == 'teenth':
            if m[2][0] < 13:
                day = m[3][0]
            else:
                day = m[2][0]
    return date(year, month, day)
