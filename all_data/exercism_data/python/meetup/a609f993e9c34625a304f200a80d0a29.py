import datetime


def meetup_day(year, month, weekday, description):
    '''returns date of meetup'''
    if description == "teenth":
        return get_teenth(year, month, weekday)
    else:
        day_index = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, '5th' : 4, 'last':-1}
        meeting_day_index = day_index[description]
        meeting_date = get_list_of_weekdays(year, month, weekday)[meeting_day_index]
        return meeting_date
    
def get_teenth(year, month, weekday):
    '''gets the weekday that lies between 13th and 19th of month'''
    teenth = datetime.date(year, month, 13)
    while teenth.strftime('%A') != weekday:
        teenth += datetime.timedelta(days=1)
    return teenth
    
def get_list_of_weekdays(year, month, weekday):
    '''gets list of all dates of this weekday in month'''
    weekdays = []
    current_day = datetime.date(year, month, 1)
    while current_day.month == month:
        if str(current_day.strftime('%A')) == weekday:
            weekdays.append(current_day)
        current_day = current_day + datetime.timedelta(days=1)
    return weekdays
