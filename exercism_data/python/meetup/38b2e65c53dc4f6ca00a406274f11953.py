import calendar
import datetime
def meetup_day(year, month, xday, meetday):
    """
    (yyyy, m, Xday, str) -> datetime (yyyy, m, d)
    """

    basedate = datetime.date(year,month,1)
    #convert xday from string to integer
    nthday = 0
    for weekday in calendar.day_name:
        if xday == weekday:
            break
        nthday += 1
    
    #iterate over the 13th - 19th to find dayofweek(1-7) to match teenth
    if meetday == 'teenth':
        basedate = datetime.date(year,month,13)
        for i in range(8):
            if basedate.weekday() == nthday:
                return basedate
            basedate += datetime.timedelta(days=1)
        raise
    
    #1st 2nd 3rd 4th *5th* first second third fourth *fifth* last
    else:
        if meetday == "1st" or meetday == "first":
            meetday = 1
        elif meetday == "2nd" or meetday == "second":
            meetday = 2
        elif meetday == "3rd" or meetday == "third":
            meetday = 3
        elif meetday == "4th" or meetday == "fourth":
            meetday = 4
        elif meetday == "5th" or meetday == "fifth":
            meetday = 5
        else: #meetday == "last"
            meetday = -1

        #find match through entire month
        matches = 0
        cal = calendar.Calendar()
        for i in cal.itermonthdays(year, month):
            if basedate.weekday() == nthday:
                matches += 1
                lastdate = basedate
            if matches == meetday:
                return basedate
            basedate += datetime.timedelta(days=1)
        if meetday == -1:
            return lastdate
