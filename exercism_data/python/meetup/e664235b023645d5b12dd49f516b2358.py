import datetime
import calendar

def meetup_day(year, month, day_text, day_number):
    '''Returns the datetime.date object for the given parameters
    IN:
        (int) year
        (int) month
        (string) day: day of the week, Monday, Tuesday ... Sunday
        (string) day_number: indication of the exact date
    OUT:
        datetime.date object'''
    
    weekday_numbers = dict(zip(list(calendar.day_name), range(0,7)))
    
    
    # generate a list of dates that match the year, month and day_text
    cal = calendar.Calendar()
    
    matching_day_numbers = [
        x.day for x in cal.itermonthdates(year, month) 
        if x.weekday() == weekday_numbers.get(day_text) and x.month == month
    ]
    
    if 'teenth' in day_number:
        for d in matching_day_numbers:
            if d in range(13,21):
                return datetime.date(year,month,d)
    elif 'last' in day_number:
        return datetime.date(year,month,matching_day_numbers[-1])
    else:
        day_number_stripped = int(filter(lambda x: x.isdigit(), day_number))
        return datetime.date(year,month,matching_day_numbers[day_number_stripped-1])
