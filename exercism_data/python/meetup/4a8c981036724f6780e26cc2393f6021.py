import datetime

class MeetupDayException(BaseException):
    pass

def meetup_day(year, month, weekday, qualifier):
    candidates = []
    weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

    date = datetime.date(year, month, 1)
    while True:
        next_day = date + datetime.timedelta(days=1)

        if date.weekday() == weekdays.index(weekday):
            candidates.append(date)

        if date.month != next_day.month:
            break

        date = next_day

    if qualifier == '1st':
        return candidates[0]

    if qualifier == '2nd':
        return candidates[1]

    if qualifier == '3rd':
        return candidates[2]

    if qualifier == '4th':
        return candidates[3]

    if qualifier == '5th':
        if len(candidates) == 5:
            return candidates[4]
        else:
            raise MeetupDayException

    if qualifier == 'last':
        return candidates[-1]

    if qualifier == 'teenth':
        for candidate in candidates:
            if 12 < candidate.day < 20:
                return candidate

    raise MeetupDayException
