import datetime as dt


class MeetupDayException(Exception):

    def __init__(self, value):
        self.value = value

    def __str__(self):
        return repr(self.value)


def meetup_day(year, month, day, week):
    days = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3,
            'Friday': 4, 'Saturday': 5, 'Sunday': 6}

#   adjust weekday
    meeting = dt.date(year, month, 1)
    if meeting.weekday() != days[day]:
        meeting = meeting + dt.timedelta((days[day] - meeting.weekday()) % 7)

#  adjust week
    if week == "last":
        meeting += dt.timedelta(weeks=4)
        if meeting.month != month:
            meeting -= dt.timedelta(weeks=1)
    elif week == "teenth":
        if meeting.day < 6:
            meeting += dt.timedelta(weeks=2)
        else:
            meeting += dt.timedelta(weeks=1)
    elif week == "1st":
        #  do nothing
        pass
    elif week == "2nd":
        meeting += dt.timedelta(weeks=1)
    elif week == "3rd":
        meeting += dt.timedelta(weeks=2)
    elif week == "4th":
        meeting += dt.timedelta(weeks=3)
    elif week == "5th":
        meeting += dt.timedelta(weeks=4)
    else:
        raise ValueError("Illegal argument for week")

    if meeting.month != month:
        raise MeetupDayException("Invalid week")

    return meeting
