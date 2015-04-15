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
    
    possible_dates = [
        x for x in cal.itermonthdates(year, month) 
        if x.weekday() == weekday_numbers.get(day_text) and x.month == month
    ]
    
    if 'teenth' in day_number:
        for d in possible_dates:
            if d.day in range(13,21):
                return d
    elif 'last' in day_number:
        return possible_dates[-1]
    elif any(char.isdigit for char in day_number):
        day_number_stripped = int(filter(lambda x: x.isdigit(), day_number))
        return possible_dates[day_number_stripped-1]
    else:
        Exception("ERROR: Input does not follow the format.")
        
