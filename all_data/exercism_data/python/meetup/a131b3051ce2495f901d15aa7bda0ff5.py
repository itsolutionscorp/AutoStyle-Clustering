from calendar import monthrange
from datetime import date
import re

DAYNAMES = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
ORDINAL_RE = re.compile(r'(?P<ordinal>\d+)(?:st|nd|rd|th)')


class MeetupDayException(Exception):
    pass


def meetup_day(year: int, month: int, weekday: str, modifier: str) -> date:
    weekday_index = DAYNAMES.index(weekday)
    modifier_match = ORDINAL_RE.match(modifier)

    if modifier_match:
        week_number = int(modifier_match.groups()[0]) - 1

        month_start = date(year, month, 1)
        first_day = month_start.toordinal()
        difference = weekday_index - ((first_day + 6) % 7)

        meetup_date = date.fromordinal(
            first_day + (week_number * 7) + (7 + difference if difference < 0 else difference)
        )

        if meetup_date.month != month:
            raise MeetupDayException

        return meetup_date
    elif modifier == 'last':
        _, ndays = monthrange(year, month)
        month_end = date(year, month, ndays)
        last_day = month_end.toordinal()
        difference = weekday_index - ((last_day + 6) % 7)

        meetup_date = date.fromordinal(
            last_day - (7 + difference if difference < 0 else difference)
        )

        return meetup_date
    elif modifier == 'teenth':
        month_teenth = date(year, month, 13)
        thirteenth_day = month_teenth.toordinal()
        difference = weekday_index - ((thirteenth_day + 6) % 7)

        meetup_date = date.fromordinal(
            thirteenth_day + (7 + difference if difference < 0 else difference)
        )

        return meetup_date
    else:
        raise NotImplementedError
