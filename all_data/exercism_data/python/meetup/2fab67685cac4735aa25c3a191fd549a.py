#!/usr/bin/python
# exercism python 7: meetup

import datetime
import re

def day_gen(year, month):
    for day in xrange(1,32):
        try:
            yield datetime.date(year, month, day)
        except ValueError:
            raise StopIteration

def meetup_day(year, month, weekday, ordinal):
    days_of_the_week = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 
                                                                                  'sunday']
    if weekday.lower() not in days_of_the_week:
        return 'Bad weekday'
    else:
        weekday = days_of_the_week.index(weekday.lower())
    
    if re.match('^\d[SsNnRrTt]*[TtDdHh]*$', ordinal):
        return definite_ordinal(year, month, weekday, ordinal)
    elif ordinal == 'teenth':
        return teenth(year, month, weekday)
    elif ordinal == 'last':
        return last(year, month, weekday)
    else:
        return None

def definite_ordinal(year, month, weekday, ordinal_string):
    ordinal = int(ordinal_string[0])
    day_count = 0
    for date_obj in day_gen(year, month):
        if date_obj.weekday() == weekday:
            day_count += 1        
        if day_count == ordinal:
            return date_obj
    return None

def teenth(year, month, weekday):
    for index, date_obj in enumerate(day_gen(year, month)):
        if 10 < index + 1 < 20 and date_obj.weekday() == weekday:
            return date_obj
    return None
    
def last(year, month, weekday):
    return_obj = datetime.date(1900, 1, 1)
    for date_obj in day_gen(year, month):
        if date_obj.weekday() == weekday:
            return_obj = date_obj
    return return_obj

    
    
    
