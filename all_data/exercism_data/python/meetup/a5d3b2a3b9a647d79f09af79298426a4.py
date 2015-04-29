from datetime import date, timedelta

def meetup_day(year, month, weekday, suffix):
    '''Given a formatted date like
    (year=2013, month=5, weekday=Monday, suffix=teenth|nth|last)
    returns a datetime object.
    '''

    meetup = date(year=year, month=month, day=1)
    possible_days = []
    while (meetup.month == month):
        if meetup.strftime('%A') == weekday.capitalize():
            possible_days.append(int(meetup.day))
        meetup += timedelta(days=1)

    if suffix == 'teenth':
        for d in possible_days:
            if d > 10:
                day = d
                break
    elif suffix == 'last': day = possible_days[-1]
    else: day = possible_days[int(suffix[:-2]) - 1]

    return date(year=year, month=month, day=day)
