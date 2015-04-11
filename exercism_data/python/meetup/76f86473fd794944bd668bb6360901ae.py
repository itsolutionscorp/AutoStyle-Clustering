__author__ = 'agupt15'

import datetime


class MeetupDayException(Exception):
    pass


def meetup_day(year, month, week_day, qualifier):
    QUALIFIERS = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, '5th': 4, 'last': -1, 'teenth': 11}
    WEEK_DAYS = {0: 'Monday', 1: 'Tuesday', 2: 'Wednesday', 3: 'Thursday', 4: 'Friday', 5: 'Saturday', 6: 'Sunday'}

    current_day = datetime.date(year, month, 1)
    relevant_days = list()

    for _ in range(31):
        wd = WEEK_DAYS[current_day.weekday()]
        if wd == week_day:
            relevant_days.append(current_day)
        current_day += datetime.timedelta(days=1)
        # # Month is finished. Break iteration.
        if current_day.month > month:
            break

    print(relevant_days)

    if qualifier != 'teenth':
        try:
            qualifier_index = QUALIFIERS[qualifier]
            return relevant_days[qualifier_index]
        except IndexError:
            raise MeetupDayException()
    else:
        for day in relevant_days:
            if day.day >= 13:
                return day

    return None
