import calendar
import datetime
import collections
import re

teenth = (13,14,15,16,17,18,19)
numbers = re.compile("[0-9]+")
one_day = datetime.timedelta(1)
days_of_week = {
    "Sunday": 6,
    "Monday": 0,
    "Tuesday": 1,
    "Wednesday": 2,
    "Thursday": 3,
    "Friday": 4,
    "Saturday": 5,
}

class MeetupDayException(ValueError):
    pass

def weekdays(year, month):
    weekdays = collections.defaultdict(lambda: [])
    first = datetime.date(year, month, 1)
    _, days_in_month = calendar.monthrange(year, month)

    for day in xrange(days_in_month):
        this_day = first + (one_day * day)
        weekdays[this_day.weekday()].append(this_day)

    return weekdays

def meetup_day(year, month, weekday, identifier):
    days = weekdays(year, month)
    day_of_week = days_of_week[weekday]

    if identifier == "last":
        day = days[day_of_week][-1].day

    elif identifier == "teenth":
        for possible in days[day_of_week]:
            if possible.day in teenth:
                day = possible.day
                break
        else:
            raise MeetupDayException

    elif re.match(numbers, identifier) is not None:
        day_index = int(re.findall(numbers, identifier)[0])
        day_index -= 1

        try:
            day = days[day_of_week][day_index].day
        except IndexError as e:
            raise MeetupDayException(e)
    else:
        raise MeetupDayException

    return datetime.date(year, month, day)
