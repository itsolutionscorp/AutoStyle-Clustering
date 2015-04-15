from datetime import date
import calendar

class MeetupDayException(Exception):
    pass

def meetup_day(year, month, day, meetup):
    weekday_idx = list(calendar.day_name).index(day)
    month_calendar = calendar.monthcalendar(year, month)
    first_occurrence = (month_calendar[0][weekday_idx], 0) \
            if month_calendar[0][weekday_idx] != 0 else (month_calendar[1][weekday_idx], 1)

    if meetup == '1st':
        return date(year, month, first_occurrence[0])
    elif meetup == '2nd':
        return date(year, month, month_calendar[first_occurrence[1] + 1][weekday_idx])
    elif meetup == '3rd':
        return date(year, month, month_calendar[first_occurrence[1] + 2][weekday_idx])
    elif meetup == '4th':
        return date(year, month, month_calendar[first_occurrence[1] + 3][weekday_idx])
    elif meetup == '5th':
        try:
            return date(year, month, month_calendar[first_occurrence[1] + 4][weekday_idx])
        except:
            raise MeetupDayException
    elif meetup == 'last':
        return date(year, month, month_calendar[-1][weekday_idx])
    elif meetup == 'teenth':
        day = first_occurrence[0] + 7 if first_occurrence[0] + 7 >= 10 else first_occurrence[0] + 14
        return date(year, month, day)
    else:
        raise MeetupDayException
