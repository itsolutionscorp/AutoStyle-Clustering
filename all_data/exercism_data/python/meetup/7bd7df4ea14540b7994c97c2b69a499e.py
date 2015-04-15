from datetime import date, timedelta

class MeetupDayException(Exception):
    pass

_WEEKDAY_NAMES = 'monday tuesday wednesday thursday friday saturday sunday'
_WEEKDAYS = _WEEKDAY_NAMES.split()
_WEEKDAY_NAME_TO_INT = dict(zip(_WEEKDAYS, range(len(_WEEKDAYS))))
_WEEKDAY_INT_TO_NAME = dict(zip(range(len(_WEEKDAYS)), _WEEKDAYS))

def meetup_day(year, month, event_day_name, which_instance):
    event_day_name = event_day_name.lower()
    first_day_of_month = date(year, month, 1)
    first_day_name = _WEEKDAY_INT_TO_NAME[first_day_of_month.weekday()]

    first_date_for_event_weekday_after_days = _WEEKDAY_NAME_TO_INT[
            event_day_name] - first_day_of_month.weekday()
    if first_date_for_event_weekday_after_days < 0:
        first_date_for_event_weekday_after_days = (
                7 + first_date_for_event_weekday_after_days)
    first_date_for_event_weekday = first_day_of_month + timedelta(
            first_date_for_event_weekday_after_days)

    return _get_nth_weekday(first_date_for_event_weekday, which_instance)

def _get_nth_weekday(first_date_for_event_weekday, which_instance):
    if which_instance[0].isdigit():
        week_number = int(which_instance[0])
        week_difference = week_number - 1
        final_date = first_date_for_event_weekday + (
                timedelta(weeks=week_difference))
        if final_date.month != first_date_for_event_weekday.month:
            raise MeetupDayException
    elif which_instance == 'teenth':
        final_date = first_date_for_event_weekday
        final_date_day = final_date.day
        week_difference = 0
        while final_date_day < 12:
            final_date_day += 7
            week_difference += 1
        final_date = first_date_for_event_weekday + timedelta(
                weeks=week_difference)
    else:
        # For 'last'
        next_week = first_date_for_event_weekday
        while next_week.month == first_date_for_event_weekday.month:
            final_date = next_week
            next_week = next_week + timedelta(weeks=1)

    return final_date
