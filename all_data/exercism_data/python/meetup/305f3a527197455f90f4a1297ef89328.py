import datetime as dt
import calendar

number_to_index = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1}


def meetup_day(year, month, day_of_week, number):
    month_calendar = calendar.monthcalendar(year, month)
    day_number = getattr(calendar, day_of_week.upper())
    dates_in_month = [week[day_number] for week in month_calendar if week[day_number]]

    if number == 'teenth':
        date = [date for date in dates_in_month if date >= 13][0]
    else:
        date = dates_in_month[number_to_index[number]]

    return dt.date(year, month, date)
