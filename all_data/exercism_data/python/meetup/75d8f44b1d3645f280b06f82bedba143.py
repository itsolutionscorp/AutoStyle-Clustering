import datetime

day_converter = {'Monday':0,
       'Tuesday':1,
       'Wednesday':2,
       'Thursday':3,
       'Friday':4,
       'Saturday':5,
       'Sunday':6}
increments = {'1st':0, '2nd':1, '3rd':2, '4th':3, 'teenth':-10, 'last':-1}

def meetup_day(year, month, weekday, position):
    weekday = day_converter[weekday]
    date = datetime.date(year, month, 1)
    day = date.weekday()
    
    # gets to the right day of the week
    while day != weekday:
        date += datetime.timedelta(1)
        day = date.weekday()
        
    increment = increments[position]
    
    if increment >= 0:
        date += datetime.timedelta(7 * increment)
    
    if increment == -1:
        date += datetime.timedelta(28)
        
        # Overshot the date, whoops!
        if date.month != month:
            date -= datetime.timedelta(7)
    
    if increment == -10:
        day = date.day
        
        while day < 10:
            date += datetime.timedelta(7)
            day = date.day
        
    return date
