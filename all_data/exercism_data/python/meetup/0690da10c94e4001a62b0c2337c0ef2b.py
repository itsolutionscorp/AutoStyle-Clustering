import datetime


WEEKDAY_STR_TO_INT = {
        "Monday": 1,
        "Tuesday": 2,
        "Wednesday": 3,
        "Thursday": 4,
        "Friday": 5,
        "Saturday": 6,
        "Sunday": 7
}


def meetup_day(year, month, weekday_str, day_choice):
    weekday = WEEKDAY_STR_TO_INT[weekday_str]

    if day_choice == 'last':
        return _last_date_for(year, month, weekday)

    first_date = _first_date_for(year, month, weekday)

    if day_choice == '1st':
        delta_weeks = 0
    elif day_choice == '2nd':
        delta_weeks = 1
    elif day_choice == 'teenth' and first_date.day > 5:
        delta_weeks = 1
    elif day_choice == '3rd':
        delta_weeks = 2
    elif day_choice == 'teenth' and first_date.day <= 5:
        delta_weeks = 2
    elif day_choice == '4th':
        delta_weeks = 3
    elif day_choice == '5th':
        delta_weeks = 4
    else:
        raise Exception("invalid value for 'day_choice'")

    return first_date + datetime.timedelta(weeks=delta_weeks)


def _first_date_for(year, month, weekday):
    first_of_month = datetime.date(year, month, 1)
    days_to_first_weekday = (weekday - first_of_month.isoweekday()) % 7

    return first_of_month + datetime.timedelta(days=days_to_first_weekday)


def _last_date_for(year, month, weekday):
    # start at the maximum possible last_day for any month,
    # then let datetime.date() throw exceptions for invalid dates,
    # instead of performing these invalid checks ourselves
    last_day = 31
    while last_day > 27:
        try:
            date = datetime.date(year, month, last_day)
            return date
        except ValueError:
            last_day -= 1

    raise Exception('cannot determine last date for given year, month')
