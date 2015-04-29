from datetime import date as d
import calendar

def meetup_day(year,month,day,week):
    week_dict = {'1st': (1,8),
                 '2nd': (8, 15),
                 '3rd': (15,22),
                 '4th': (21, 29),
                 'teenth': (13,20)}
    weekday = {'Monday': 0,
               'Tuesday': 1,
               'Wednesday': 2,
               'Thursday': 3,
               'Friday': 4,
               'Saturday': 5,
               'Sunday': 6}
    if week == 'last':
        last_day = calendar.monthrange(year,month)[1]
        for num in range(last_day,last_day-7,-1):
            if d(year, month, num).weekday() == weekday[day]:
                return d(year, month, num)
    else:
        for num in range(week_dict[week][0], week_dict[week][1]):
            if d(year, month, num).weekday() == weekday[day]:
                return d(year, month, num)
