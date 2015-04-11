from datetime import date
from calendar import Calendar


def meetup_day(year, month, day, suffix):
    day = day.lower()
    days_iterator = Calendar().itermonthdays2(year, month)
    weekdays = (
        'monday', 
        'tuesday', 
        'wednesday', 
        'thursday', 
        'friday', 
        'saturday', 
        'sunday',
    )
    
    # day date num (0-31), weekday num (0-6)
    for d, wd in days_iterator:

        # if it's not our weekday, take next
        if day != weekdays[wd]:
            continue

        if suffix == 'teenth' and 13 <= d <= 19:
            meetup_day = d
            break  
        # first weekday in the week is between 1 and 8
        if suffix == '1st' and  0 < d <= 7:
            meetup_day = d
            break  
        # day happens to be in the week only once
        # so 2nd should be somewhere in the 2nd week, so on...
        if suffix == '2nd' and 7 < d <= 14:
            meetup_day = d
            break  

        if suffix == '3rd' and 14 < d <= 21:
            meetup_day = d
            break  

        if suffix == '4th' and 22 < d <= 31:
            meetup_day = d
            break  

        elif suffix == 'last' and (d - 7) > 21:
            meetup_day = d
            break

    return date(year, month, d)
