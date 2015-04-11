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
    # day search by index contains number at the beginning
    # so let's scrap it out
    try:
        day_num = int(suffix[0])
    except ValueError:
        day_num = 0

    # day date num (0-31), weekday num (0-6)
    for d, wd in days_iterator:
        # if it's not our weekday, take next one
        if day != weekdays[wd]:
            continue

        # now let's use that scraped number
        if day_num and  (day_num-1)*7 < d <= day_num*7:
            meetup_day = d
            break  

        if suffix == 'teenth' and 13 <= d <= 19:
            meetup_day = d
            break  

        elif suffix == 'last' and (d - 7) > 21:
            meetup_day = d
            break

    return date(year, month, d)
