import datetime
def meetup_day(year, month, weekday, qualifier):
    deltadays = 0
    d1 = datetime.date(year,month,1)
    next_month = d1.replace(day=28) + datetime.timedelta(days=4)
    df = next_month - datetime.timedelta(days=next_month.day)
    weekdays = {
        'Monday': 0,
        'Tuesday':1,
        'Wednesday':2,
        'Thursday':3,
        'Friday':4,
        'Saturday': 5,
        'Sunday': 6
    }
    
    multiplier = {
        '1st':0,
        '2nd':1,
        '3rd':2,
        '4th':3,
        'teenth':0
    }
    
    DayNum = weekdays[weekday]
    
    if qualifier in ['1st', '2nd', '3rd' , '4th','teenth']:
        if qualifier == 'teenth':
            d1 = d1.replace(day=13)
        deltadays = DayNum - d1.weekday() 
        if deltadays < 0:
            deltadays += 7
        deltadays = multiplier[qualifier]*7 + deltadays
        d = d1
    elif qualifier == 'last':
        deltadays = df.weekday() - DayNum
        d = df
    return d + datetime.timedelta(deltadays)
