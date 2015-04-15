import calendar
import datetime

WEEKDAY_NAMES = list(calendar.day_name)

RULES_DICT = {'1st'   : lambda d : d[0],
              '2nd'   : lambda d : d[1],
              '3rd'   : lambda d : d[2],
              '4th'   : lambda d : d[3],
              'first' : lambda d : d[0],
              'last'  : lambda d : d[-1],
              'teenth': lambda d : list(filter(lambda x : x > 12 and x < 20, d))[0],
              }

def meetup_day(year,
               month,
               weekday_name,
               rule, ):

    year = int(year)
    month = int(month)
        
    days_list = [monthday
                 for monthday, weekday
                 in calendar.Calendar().itermonthdays2(year, month)
                 if weekday == WEEKDAY_NAMES.index(weekday_name)
                 and monthday != 0]

    return datetime.date(year, month, RULES_DICT[rule](days_list))
