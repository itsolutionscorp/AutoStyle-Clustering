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

    # create iterator of only neccessary days (redundant maybe)
    for d, wd in (dtup for dtup in days_iterator 
                    if day == weekdays[dtup[1]] ):

        # we can refactor if-elif as or chain 
        # to remove code duplication
        if  (day_num and  (day_num-1)*7 < d <= day_num*7) or \
            (suffix == 'teenth' and 13 <= d <= 19) or \
            (suffix == 'last' and (d - 7) > 21):
                meetup_day = d
                break
    
    # we could try/check it but spec doesn't say anything 
    # about what value to return on error
    # so ValueError thrown by date seems ok to me
    return date(year, month, d)
