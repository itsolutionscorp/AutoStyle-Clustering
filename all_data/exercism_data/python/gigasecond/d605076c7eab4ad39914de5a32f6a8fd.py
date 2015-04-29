import datetime


from datetime import date


def add_gigasecond(olddate):
    epoch = int(float(olddate.strftime('%s'))) + 1000000000
    fulldate = datetime.datetime.fromtimestamp(epoch)
    return date(fulldate.year, fulldate.month, fulldate.day)
