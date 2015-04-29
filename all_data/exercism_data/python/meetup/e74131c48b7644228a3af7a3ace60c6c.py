from datetime import date
import calendar


weekdays = dict((k, v) for (v, k) in enumerate(['monday',
                                                'tuesday',
                                                'wednesday',
                                                'thursday',
                                                'friday',
                                                'saturday',
                                                'sunday']))

                                                  
def meetup_day(year, month, day_text, ordinal_text):
    weekday = weekdays[day_text.lower()]
    day = None
    
    if ordinal_text == 'last':
        day = calendar.monthrange(year, month)[1]
        
    if ordinal_text == 'teenth':
        first_teenth = date(year, month, 13).weekday()
        day = 13 + (weekday - first_teenth + 7) % 7
        
    if ordinal_text[0].isdigit():
        ordinal = int(ordinal_text[:-2]) - 1
        
        first_day_month = date(year, month, 1).weekday()
        day = 1 + (weekday - first_day_month) + 7 * ordinal
        
        if weekday < first_day_month:
            day += 7
    
    return date(year, month, day)
