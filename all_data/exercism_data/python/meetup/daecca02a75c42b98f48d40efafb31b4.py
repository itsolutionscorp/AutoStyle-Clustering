from datetime import date, timedelta

DOW2ISOWEEKDAY = {
    'Monday'   : 1,
    'Tuesday'  : 2,
    'Wednesday': 3,
    'Thursday' : 4,
    'Friday'   : 5,
    'Saturday' : 6,
    'Sunday'   : 7
}

def meetup_day(y, m, dow, grouping):
    target_isoweekday = DOW2ISOWEEKDAY.get(dow)

    if grouping == 'teenth':
        candidates = range(13,20)
    elif grouping == 'last':
        if m == 12:
            last_day = 31
        else:
            last_day = (date(y, m+1, 1)-timedelta(days=1)).day
        candidates = range(last_day, last_day-7, -1)
    else: # 1st, 2nd, 3rd, 4th
        i = int(grouping[0])
        candidates = range((i-1)*7+1,i*7+1)
    for day in candidates:
        d = date(y, m, day)
        if d.isoweekday() == target_isoweekday:
            return d
