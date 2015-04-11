from datetime import date, timedelta

def meetup_day(year, month, weekday, suffix):
    '''Given a formatted date like
    (year=2013, month=5, weekday=Monday, suffix=teenth|nth|last)
    returns a datetime object.
    '''

    meetup = date(year, month, 1)
    possible_days = []
    while (meetup.month == month):
        if meetup.strftime('%A') == weekday.capitalize():
            possible_days.append(meetup.day)
        meetup += timedelta(days=1)

    if suffix == 'teenth':
        day = next(d for i, d in enumerate(possible_days) if d > 10)
    elif suffix == 'last':
        day = possible_days[-1]
    else:
        index = int(suffix[:-2]) - 1
        try:
            day = possible_days[index]
        except IndexError:
            raise MeetupDayException

    return date(year, month, day)



class MeetupDayException(Exception):
    pass
