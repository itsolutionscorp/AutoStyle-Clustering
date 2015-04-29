import datetime
from calendar import monthrange
def meetup_day(year, month, day, ordinal):
#Get last day of the month and subtract one week
    last = monthrange(year, month)[1] - 6
#Mapping of ordinal and earliest occurence
    start_on_day = {'1st': 1, '2nd': 7, '3rd': 14, 'teenth': 13, '4th': 21, 'last': last}
    d = datetime.date(year, month, start_on_day[ordinal])
#Add one day to the start date until it matches the input day
    while d.strftime("%A") != day:
        d = d + datetime.timedelta(days=1)
    return d
