# # # # # # # #
# Hate this...  #
# # # # # # # #

import calendar
import datetime


class MeetupDayException(Exception):

    """docstring for MeetupDayException"""

    def __init__(self, arg):
        super(MeetupDayException, self).__init__()
        self.arg = arg


def meetup_day(year, month, day_of_week, option):
    week = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun']
    teens = range(13, 20)
    target_day = [week.index(day) for day in week
                  if day_of_week.lower().startswith(day)].pop()

    meetup = []
    for (day, weekday) in calendar.Calendar().itermonthdays2(year, month):
        if weekday == target_day and day != 0:
            meetup.append(day)

    meet_day = {'1st': meetup[0], '2nd': meetup[1], '3rd': meetup[2],
                '4th': meetup[3], 'last': meetup[-1],
                'first': meetup[0],
                'teenth': [x for x in meetup if x in teens].pop()}
    try:
        if option == '5th':
            meet_day['5th'] = meetup[4]
        return datetime.date(year, month, meet_day[option])
    except Exception:
        raise MeetupDayException("")
