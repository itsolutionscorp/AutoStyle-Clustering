import datetime
import calendar

def get_teenth(days): 
    return filter(lambda (dn, dc): dn >= 13 and dn <= 19, days)[0][0]
def get_first(days):  return days[0][0]
def get_second(days): return days[1][0]
def get_third(days):  return days[2][0]
def get_fourth(days): return days[3][0]
def get_last(days):   return days[-1][0]

RULES = { 'teenth': get_teenth,
          '1st':    get_first,
          '2nd':    get_second,
          '3rd':    get_third,
          '4th':    get_fourth,
          'last':   get_last }

def meetup_day(year, month, day, pos):
    days = calendar.Calendar().itermonthdays2(year, month)
    try:
        daycode = list(calendar.day_name).index(day)
        possible_days = filter(lambda (dn, dc): dn > 0 and dc == daycode, days)
        resulting_day = RULES[pos](possible_days)
        return datetime.date(year, month, resulting_day)
    except:
        return None
