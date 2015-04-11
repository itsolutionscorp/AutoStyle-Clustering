from datetime import date as Date
import calendar

DEFAULT_CONDITIONS  = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3,
                       'last':-1, 'teenth' : 'teenth'}

def meetup_day(year, month, weekday, condition, condition_dict=DEFAULT_CONDITIONS):
    """
    calculates the date of meetups

    year (int): year
    month (int): month
    weekday (str): day of week, e.g. 'Monday'
    condition (str): e.g. '1st', '2nd', '3rd', '4th', 'last', 'teenth'
    """
    # initialization and error handling
    weekday_names = [day for day in list(calendar.day_name)]
    weekday_dict = dict(zip(weekday_names, range(0, 7)))
    date = Date(year, month, 1)             # convert to Date object
    weekday = weekday_dict[weekday]         # swap value to numeric weekday
    condition = condition_dict[condition]   # swap value to numeric condition
    
    # call get_weekdays() to get list of weekdays
    weekdays = get_weekdays(date, weekday)

    # return either the teenth or the i-th index of weekdays
    if condition == 'teenth':
        for day in weekdays:
            if 13 <= day.day <= 19:
                return day
    else:
        return weekdays[condition]

def get_weekdays(date, weekday):
    """
    finds dates in the same month as 'date' that land on a 'weekday'
    
    date (Date): provides which month and year to check, day ignored
    weekday (int): weekday represented as an integer: 0=mon to 6=sun
    returns (list): 'Date' objects in month that match weekday
    """
    year = date.year
    month = date.month
    monthcalendar = calendar.monthcalendar(year, month)
    weekdays = []

    for week in monthcalendar:
        day = week[weekday]
        if day != 0:
            weekdays.append(Date(year, month, day))

    return weekdays
