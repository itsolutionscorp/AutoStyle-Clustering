import datetime

def meetup_day(y, m, dow, end):
    d = datetime.date(y, m, 1)
    if len(end) == 3:
        pos_dow = int(end[0])
        ndow = 0
    while True:
        if d.strftime('%A').lower() == dow.lower():
            if len(end) == 3:
                ndow += 1
                if ndow == pos_dow:
                    result = d
                    break
            elif end == 'last':
                result = d
            else:
                if 13 <= d.day <= 19:
                    result = d
                    break
        d = d + datetime.timedelta(days=1)
        if d.month != m:
            break
    return result
