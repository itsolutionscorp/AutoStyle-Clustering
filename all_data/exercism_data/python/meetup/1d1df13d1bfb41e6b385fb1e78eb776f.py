import calendar
from datetime import date

weekdays = ( "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" )
weekday_lookup = { name: day for day, name in enumerate(weekdays) } # transpose the tuple

weeks = ( "1st", "2nd", "3rd", "4th" )
week_lookup = { name: week for week, name in enumerate(weeks) } # transpose the tuple

def meetup_day(year, month, weekday, week):
    weekday = weekday_lookup[weekday]
    month_calendar = calendar.monthcalendar(year, month)

    if week in week_lookup:
        week_of_month = week_lookup[week]
        if month_calendar[0][weekday] == 0:
            week_of_month += 1
    elif week == "last":
        week_of_month = -1
        if month_calendar[week_of_month][weekday] == 0:
            week_of_month -= 1
    elif week == "teenth":
        week_of_month = 1
        while month_calendar[week_of_month][weekday] < 13:
            week_of_month += 1

    return date(year, month, month_calendar[week_of_month][weekday])
