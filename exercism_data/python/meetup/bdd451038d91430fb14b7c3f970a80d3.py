from datetime import date
import calendar

def meetup_day(year, month, dotw, week_pos):
    weekdays = {'Monday':0,
                'Tuesday':1,
                'Wednesday':2,
                'Thursday':3,
                'Friday':4,
                'Saturday':5,
                'Sunday':6}
                
    cal = calendar.monthcalendar(year, month)
    dotw = weekdays[dotw]
    if week_pos == '1st':
        week = 0
        while cal[week][dotw] == 0:
            week += 1
        return date(year, month, cal[week][dotw])
    if week_pos == '2nd':
        week = 0
        while cal[week][dotw] == 0:
            week += 1
        return date(year, month, cal[week+1][dotw])
    if week_pos == '3rd':
        week = 0
        while cal[week][dotw] == 0:
            week += 1
        return date(year, month, cal[week+2][dotw])
    if week_pos == '4th':
        week = 0
        while cal[week][dotw] == 0:
            week += 1
        return date(year, month, cal[week+3][dotw])
    if week_pos == 'last':
        week = cal.index(cal[-1])
        while cal[week][dotw] == 0:
            week -= 1
        return date(year, month, cal[week][dotw])
    if week_pos == 'teenth':
        week = 0
        while cal[week][dotw] not in range(13,20):
            week += 1
        return date(year, month, cal[week][dotw])
