from datetime import date, timedelta

class MeetupDayException(Exception):
    pass

WEEKDAY_NAME_TO_NUM = {
    "monday": 0,
    "tuesday": 1,
    "wednesday": 2,
    "thursday": 3,
    "friday": 4,
    "saturday": 5,
    "sunday": 6
}
NTH_OCCURENCE_NAME_TO_WEEK_OFFSET = {
    "first": 0,
    "1st": 0,
    "second": 1,
    "2nd": 1,
    "third": 2,
    "3rd": 2,
    "fourth": 3,
    "4th": 3,
    "fifth": 4,
    "5th": 4
}

def _get_first_weekday_occurence(year, month, weekday_name):
    first_of_the_month = date(year, month, 1)
    weekday = WEEKDAY_NAME_TO_NUM[weekday_name]
    days_til_first_occurence = (weekday - first_of_the_month.weekday() + 7) % 7
    return first_of_the_month + timedelta(days=days_til_first_occurence)

def _get_last_weekday_occurence(first_occurence):
    # 'last' is either the 5th or 4th occurence and the easiest way to find
    # out is to just assume it's the 5th and subtract a week if we overshoot
    last_occurence = first_occurence + timedelta(days=28)
    if last_occurence.month > first_occurence.month:
        last_occurence -= timedelta(days=7)
    return last_occurence

def _get_teenth_weekday_occurence(first_occurence):
    # 1..5 => 15..19
    if first_occurence.day <= 5:
        return first_occurence + timedelta(days=14)
    # 6,7 => 13,14
    return first_occurence + timedelta(days=7)

def _get_nth_weekday_occurence(first_occurence, occurence_name):
    try:
        week_offset = NTH_OCCURENCE_NAME_TO_WEEK_OFFSET[occurence_name]
    except KeyError:
        raise MeetupDayException("Unknown occurence name '{}'"
                                 .format(occurence_name))

    nth_occurence = first_occurence + timedelta(weeks=week_offset)

    if nth_occurence.month != first_occurence.month:
        raise MeetupDayException("No {} occurence after first occurence: {}"
                                 .format(occurence_name, first_occurence))
    return nth_occurence

def meetup_day(year, month, weekday_name, occurence_name):
    weekday_name = weekday_name.lower()
    occurence_name = occurence_name.lower()

    first_occurence = _get_first_weekday_occurence(year, month, weekday_name)

    if occurence_name == "last":
        return _get_last_weekday_occurence(first_occurence)
    if occurence_name == "teenth":
        return _get_teenth_weekday_occurence(first_occurence)
    return _get_nth_weekday_occurence(first_occurence, occurence_name)
