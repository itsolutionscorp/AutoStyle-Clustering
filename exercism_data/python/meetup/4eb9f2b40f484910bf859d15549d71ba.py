from datetime import date

conv = {
    '1st' : 0,
    '2nd' : 1,
    '3rd' : 2,
    '4th' : 3,
    'last' : -1
}   

def meetup_day(year, month, weekday, monthday):
    possible_days = list()
    for day in range(1, (date(year, month + 1, 1) - date(year, month, 1)).days + 1):
        if date(year, month, day).strftime('%A') == weekday:
            possible_days.append(day)
    if monthday == 'teenth':
        for pd in possible_days:
            if 12 < pd < 20:
                return date(year, month, pd)
    else:
        return date(year, month, possible_days[conv[monthday]])
