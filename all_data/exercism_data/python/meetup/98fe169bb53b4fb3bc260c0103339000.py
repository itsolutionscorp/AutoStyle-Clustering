from datetime import date,timedelta
from string import maketrans

MeetupDayException = Exception

# zero-index
DAYS_IN_A_WEEK = 7

teenth = [13,14,15,16,17,18,19]

days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
weekdays = [0,1,2,3,4,5,6]
days_weekdays_dict = dict(zip(days, weekdays))

def find_first(year, month, day):

    weekday = days_weekdays_dict[day]
    day_of_month = date(year, month, 1)

    while day_of_month.weekday() is not weekday:
        day_of_month += days_later(1)

    return day_of_month

def days_later(num_of_days):
    return timedelta(days = num_of_days)

def weeks_later(num_of_weeks):
    return timedelta(days = DAYS_IN_A_WEEK * num_of_weeks)

def find_teenth(year, month, day):

    date = find_first(year, month, day)

    while date.day not in teenth:
        date += weeks_later(1)

    return date

def find_last(year, month, day):

    date = find_first(year, month, day)
    next_date = date + weeks_later(1)

    while next_date.month is month:
        date, next_date = next_date, next_date + weeks_later(1)

    return date

def find_meetup_day(year, month, day, descriptor):

        if descriptor in ['1st', 'first']:
            return find_first(year, month, day)

        elif descriptor in (['2nd','second']):
            return find_first(year, month, day) + weeks_later(1)

        elif descriptor in (['3rd', 'third']):
            return find_first(year, month, day) + weeks_later(2)

        elif descriptor in (['4th', 'fourth']):
            return find_first(year, month, day) + weeks_later(3)

        elif descriptor in (['5th', 'fifth']):
            return find_first(year, month, day) + weeks_later(4)

        elif descriptor is 'teenth':
            return find_teenth(year, month, day)

        elif descriptor is 'last':
            return find_last(year, month, day)

        else:
            raise MeetupDayException

def validate_meetup(meetup_day, year, month):

    if (meetup_day.year == year and 
        meetup_day.month == month):

        return True

    else:
        raise MeetupDayException

def meetup_day(year, month, day, descriptor):

    meetup_day = find_meetup_day(year, month, day, descriptor)

    validate_meetup(meetup_day, year, month)

    return meetup_day
