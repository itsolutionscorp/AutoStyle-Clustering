from datetime import date, timedelta

weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
whichs = {'1st': 0,
          '2nd': 1,
          'teenth': 1,
          '3rd': 2,
          '4th': 3,
          'last':4}

def meetup_day(year, month, weekday, which):
    first_of_month = date(year, month, 1)
    weekday = weekdays.index(weekday)
    first_of_weekday = first_of_month + timedelta(days=1) * ((weekday - first_of_month.weekday()) % 7)
    candidate = first_of_weekday + timedelta(weeks=1) * whichs[which]
    if which == 'teenth' and candidate.day < 13:
        return candidate + timedelta(weeks=1)
    return candidate
