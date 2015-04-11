import datetime
def meetup_day(year, month, dayofweek, occurance):
    firstofmonth = datetime.date(year,month,1).isoweekday()
    if dayofweek == 'Monday':
        meetupdayofweek = 1
    if dayofweek == 'Tuesday':
        meetupdayofweek = 2
    if dayofweek == 'Wednesday':
        meetupdayofweek = 3
    if dayofweek == 'Thursday':
        meetupdayofweek = 4
    if dayofweek == 'Friday':
        meetupdayofweek = 5
    if dayofweek == 'Saturday':
        meetupdayofweek = 6
    if dayofweek == 'Sunday':
        meetupdayofweek = 7
    def calc_first_day():
        if meetupdayofweek >= firstofmonth:
            return meetupdayofweek - firstofmonth + 1
        else:
            return meetupdayofweek - firstofmonth + 8
    if occurance == '1st':
        day = calc_first_day()
    if occurance == '2nd':
        day = calc_first_day() + 7
    if occurance == '3rd':
        day = calc_first_day() + 14
    if occurance == '4th':
        day = calc_first_day() + 21
    if occurance == 'teenth':
        if calc_first_day() > 2:
            day = calc_first_day() + 7
        else:
            day = calc_first_day() + 14
    if occurance == 'last':
        if month < 12:
            lastday = (datetime.date(year,month+1,1) - datetime.timedelta(days = 1)).day
        else:
            lastday = 31
        day = calc_first_day() + 28
        while day > lastday:
            day -= 7
    return datetime.date(year, month, day)
