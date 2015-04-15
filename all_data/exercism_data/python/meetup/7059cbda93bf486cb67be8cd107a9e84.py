import calendar
import datetime

NTH_LOOKUP = dict(zip(('1st', '2nd', '3rd', '4th', '5th'), range(5)))
NTH_LOOKUP['last'] = -1

DAY_OF_WEEK_LOOKUP = dict(zip(
    ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'),
    range(7)
))

def meetup_day(year, month, day_of_week, nth):
    month_calendar = calendar.monthcalendar(year, month)
    day_of_week = DAY_OF_WEEK_LOOKUP[day_of_week]

    candidate_days = [week[day_of_week] for week in month_calendar]

    if nth == 'teenth':
        candidate_days = [day for day in candidate_days if 13 <= day <= 19]
        assert len(candidate_days) == 1
        return datetime.date(year, month, candidate_days[0])

    nth = NTH_LOOKUP[nth] + (month_calendar[0][day_of_week] == 0)
    return datetime.date(year, month, candidate_days[nth])
