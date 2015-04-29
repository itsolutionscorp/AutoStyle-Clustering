"""
meetup.py: Program to calculate the date of meetups.
"""
import calendar

DAYS = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6,
}

TEENTH = [13, 14, 15, 16, 17, 18, 19]

WEEKS = {
    '1st': 1,
    '2nd': 2,
    '3rd': 3,
    '4th': 4,
    'last': 5
}


def meetup_day(year, month, day_of_week, week_of_month):
    # Month range
    cal = calendar.monthrange(year, month)
    # day as ordinal
    day_ordinal = DAYS[day_of_week]
    # Generator for days in month
    days_in_month = (day_ordinal for day_ordinal in range(1, calendar.monthrange(2015, 1)[1] + 1))


    # if week_of_month = 'teenth'
    # given a day of week find whether it exists in week of month
    #week_in_month = (date.day - 1) // 7 + 1
    # day_repr = WEEKS[week_in_month]
    # if day_repr in range(10, 21):
    #     day_repr = 'teenth'
    # return date(year, month, DAYS[day_of_week])
    # return date.year, date.month, DAYS[date.weekday()], day_repr
