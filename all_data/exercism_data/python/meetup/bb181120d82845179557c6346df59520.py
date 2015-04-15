from datetime import date
weekdays = dict(zip(['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday',\
                     'Saturday', 'Sunday'], range(0, 7)))

def meetup_day(year, month, day, position):
    first_day = date(year, month, 1).weekday()
    distance = weekdays[day] - first_day
    if distance < 0:
        distance += 7
    weeks = 0
    if position == 'last':
        fifth_days = []
        for i in range(31, 28, -1):
            try:
                fifth_days += [date(year, month, i).weekday()]
            except ValueError:
                pass
        if weekdays[day] in fifth_days:
            weeks = 4
        else:
            weeks = 3
    elif position == 'teenth':
        if distance in (5, 6):
            weeks = 1
        else:
            weeks = 2
    else:
        weeks = int(position[0]) - 1
        
    return date(year, month, weeks * 7 + distance + 1)
