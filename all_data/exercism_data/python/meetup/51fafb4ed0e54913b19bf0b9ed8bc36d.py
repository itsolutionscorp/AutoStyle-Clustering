from datetime import date, timedelta

def meetup_day(year, month, day, descriptor):
    
    day_of_week = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2,
            'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}

    # Begin with the first day of the month.
    meetup_date = date(year, month, 1)

    # Find the first instance of the weekday.
    while meetup_date.weekday() is not day_of_week[day]:
        meetup_date += timedelta(1) # Increment by one day.

    if descriptor == '1st':
        return meetup_date
    elif descriptor == '2nd':
        # Increment by a week.
        return meetup_date + timedelta(7)
    elif descriptor == '3rd':
        # Increment by two weeks.
        return meetup_date + timedelta(14)
    elif descriptor == '4th':
        # Increment by three weeks.
        return meetup_date + timedelta(21)
    elif descriptor == 'teenth':
        # Increment by a week until the day is between the 13th and 19th of the month.
        while meetup_date.day < 13:
            meetup_date += timedelta(7)
        return meetup_date
    elif descriptor == 'last':
        # Increment by a week until reaching a new month.
        while meetup_date.month == month:
            meetup_date += timedelta(7)
        return meetup_date - timedelta(7)
