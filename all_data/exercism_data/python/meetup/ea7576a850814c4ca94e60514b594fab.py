import calendar
import datetime

def meetup_day(year,
               month,
               weekday_name,
               cardinality, ):

    weekday_dict = {'Monday'   : 0,
                    'Tuesday'  : 1,
                    'Wednesday': 2,
                    'Thursday' : 3,
                    'Friday'   : 4,
                    'Saturday' : 5,
                    'Sunday'   : 6}
    
    # The first week in which a 'teenth' can occur is
    # week 2. If it's not in week 2, then the 'teenth'
    # has to occur in week 3.
    # We'll add another check in the end, to make sure
    # that we got the right day.
    card_dict = {'1st'   : 0,
                 '2nd'   : 1,
                 '3rd'   : 2,
                 '4th'   : 3,
                 'first' : 0,
                 'last'  : -1,
                 'teenth': 1,
                 }

    year = int(year)
    month = int(month)
        
    days_list = [monthday for monthday, weekday
                 in calendar.Calendar().itermonthdays2(year, month)
                 if weekday == weekday_dict[weekday_name]
                 and monthday != 0]
    
    day = days_list[card_dict[cardinality]]

    if cardinality == 'teenth' and day < 13:
        day = days_list[card_dict[cardinality] + 1]

    return datetime.date(year, month, day)
