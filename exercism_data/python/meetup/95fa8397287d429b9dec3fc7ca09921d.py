import datetime, calendar

WEEK_MAP = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday"
]

def meetup_day(year, month, day_name, when):
    if when == 'teenth':
        start = 13
    elif when == '1st':
        start = 1
    elif when == '2nd':
        start = 8
    elif when == '3rd':
        start = 15
    elif when == '4th':
        start = 22
    elif when == 'last':
        start = calendar.monthrange(year, month)[1]-6

    for day_index in range(start, start+7):
        this_date = datetime.date(year, month, day_index)

        if this_date.weekday() is WEEK_MAP.index(day_name):
            return this_date
