from datetime import date, timedelta


class MeetupDayException(Exception):
    pass


def _map_weekday_to_number(weekday):
    ''' Maps an English-language weekday to a corresponding number. '''

    return {
        'monday': 0,
        'tuesday': 1,
        'wednesday': 2,
        'thursday': 3,
        'friday': 4,
        'saturday': 5,
        'sunday': 6
        }[weekday.lower()]


def meetup_day(year, month, weekday, ordinality):
    '''
    Returns a date corresponding to the inputs given, where ordinality
    is something like '1st', '2nd', 'last', 'teenth'.
    '''

    numerical_weekday = _map_weekday_to_number(weekday)

    if ordinality[0].isdigit():
        first_of_month = date(year=year, month=month, day=1)

        if first_of_month.weekday() == numerical_weekday:
            first_weekday_of_month = first_of_month
        elif first_of_month.weekday() < numerical_weekday:
            first_weekday_of_month = (
                first_of_month + timedelta(days=(numerical_weekday - first_of_month.weekday())))
        else:
            prev_monday = first_of_month - timedelta(days=first_of_month.weekday())
            first_weekday_of_month = (
                (prev_monday + timedelta(days=7)) + timedelta(days=numerical_weekday))

        meetup_day = first_weekday_of_month + timedelta(days=((int(ordinality[0]) - 1) * 7))

        if meetup_day.month != month:
            # we have gone past the end of the month
            raise MeetupDayException("The month you provided does not contain the specified day.")
        else:
            return meetup_day

    elif ordinality == 'teenth':
        first_teenth = date(year=year, month=month, day=13)
        if first_teenth.weekday() == numerical_weekday:
            return first_teenth
        else:
            return (first_teenth - timedelta(days=first_teenth.weekday())) + timedelta(days=numerical_weekday)

    elif ordinality == 'last':
        last_day_of_month = date(year=year, month=(month + 1), day=1) - timedelta(days=1)
        if last_day_of_month.weekday() == numerical_weekday:
            return last_day_of_month
        else:
            return (
                (last_day_of_month - timedelta(days=last_day_of_month.weekday())) +
                timedelta(days=numerical_weekday))

    else:
        raise MeetupDayException("Unexpected ordinality: %s" % ordinality)
