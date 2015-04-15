import datetime as dt


def _find_first_day(year, month, weekday):
    
    days_of_the_week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    base_date = dt.datetime(year, month, 1)
    base_weekday = base_date.weekday()
    n_weekday = days_of_the_week.index(weekday)
    first_day = base_date + dt.timedelta(days=(n_weekday-base_weekday)%7)
    
    return first_day


def meetup_day(year, month, weekday, rule):    
    
    first_day = _find_first_day(year, month, weekday)
    
    if rule=='1st':
        final_date = first_day
    elif rule=='2nd':
        final_date = first_day + dt.timedelta(days=7)
    elif rule=='3rd':
        final_date = first_day + dt.timedelta(days=14)
    elif rule=='4th':
        final_date = first_day + dt.timedelta(days=21)
    elif rule=='last':
        final_date = first_day + dt.timedelta(days=28)
        if final_date.month != month: final_date = first_day + dt.timedelta(days=21)
    elif rule=='teenth':
        final_date = first_day + dt.timedelta(days=7)
        while final_date.day not in range(13,20):
            final_date = final_date + dt.timedelta(days=7)

    return final_date.date()
