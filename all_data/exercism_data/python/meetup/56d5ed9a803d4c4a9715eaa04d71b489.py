from datetime import date, timedelta

def meetup_day(year, month, weekday, ordinal_number):
    weekdays = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}
    ordinal = {'1st':0, '2nd':1, '3rd':2, '4th':3, '5th':4, 'last':4, 'teenth':4}[ordinal_number]
    for i in range(1,8):
        day = date(year, month, i)
        if day.weekday() == weekdays[weekday]:
            break
    while ordinal:
        day += timedelta(7)
        if ordinal_number == 'teenth':
            if day.day>12 and day.day<20:
                break
        elif ordinal_number == 'last':
            if (day+timedelta(7)).month!=month:
                break
        ordinal-=1
    return day
