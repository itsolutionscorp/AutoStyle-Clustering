import calendar
import datetime
def meetup_day(year, month, xday, meetday):
    """
    (yyyy, m, Xday, str) -> datetime (yyyy, m, d)
    """
    nthday = list(calendar.day_name).index(xday)
    
    #iterate over the 13th - 19th to find dayofweek(1-7) to match teenth
    if meetday == 'teenth':
        basedate = datetime.date(year,month,13)
        for i in range(8):
            if basedate.weekday() == nthday:
                return basedate
            basedate += datetime.timedelta(days=1)
    else:
        meetlist = ("1st", "2nd", "3rd", "4th")
        if meetday in meetlist:
            meetday = meetlist.index(meetday)+1
        matches = 0
        basedate = datetime.date(year,month,1)
        for i in calendar.Calendar().itermonthdays(year, month):
            if basedate.weekday() == nthday:
                matches += 1
                lastdate = basedate
            if matches == meetday:
                return basedate
            basedate += datetime.timedelta(days=1)
        if meetday == "last":
            return lastdate
