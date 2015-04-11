import datetime
import calendar

RULES = { 'teenth': lambda d: filter(lambda (dn,_): dn in range(13,20), d)[0],
          '1st':    lambda d: d[0],
          '2nd':    lambda d: d[1],
          '3rd':    lambda d: d[2],
          '4th':    lambda d: d[3],
          'last':   lambda d: d[-1] }

def meetup_day(year, month, day, pos):
    days = calendar.Calendar().itermonthdays2(year, month)
    try:
        daycode = list(calendar.day_name).index(day)
        possible_days = filter(lambda (dn, dc): dn > 0 and dc == daycode, days)
        resulting_day = RULES[pos](possible_days)[0]
        return datetime.date(year, month, resulting_day)
    except:
        return None
