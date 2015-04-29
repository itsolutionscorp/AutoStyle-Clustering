import calendar
import datetime


def meetup_day(year, month, weekday, day):
    weekday_index = list(calendar.day_name).index(weekday)
    month_calendar = calendar.monthcalendar(year, month)
    if day == 'last':
        month_calendar.reverse()
        for i, week in enumerate(month_calendar):
            if week[weekday_index] != 0:
                week_index = i
                break
    elif day == 'teenth':
        teens = [13, 14, 15, 16, 17, 18, 19]
        for i, week in enumerate(month_calendar):
            if week[weekday_index] in teens:
                week_index = i
                break
    else:
        index = int(day[:-2])
        count = 0
        for i, week in enumerate(month_calendar):
            if week[weekday_index] != 0:
                count += 1
                if count == index:
                    week_index = i
                    break
    day_of_month = month_calendar[week_index][weekday_index]
    date = datetime.date(year, month, day_of_month)
    return date
