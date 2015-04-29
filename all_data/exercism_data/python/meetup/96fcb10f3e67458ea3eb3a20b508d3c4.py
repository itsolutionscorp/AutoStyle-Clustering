from datetime import *

def meetup_day(year, month, week_day, which):
    week_days = {"Monday" : 0,
                 "Tuesday" : 1,
                 "Wednesday" : 2,
                 "Thursday" : 3,
                 "Friday" : 4,
                 "Saturday" : 5,
                 "Sunday" : 6,
                 }
    weeks = {"1st" : 0,
             "2nd" : 1,
             "3rd" : 2,
             "4th" : 3,
             "5th" : 4,
             "teenth" : 2,
             "last" : 4
             }
    wd = week_days[week_day]
    first = date(year, month, 1)
    fd = first.weekday()
    gap = (wd - fd) % 7 + weeks[which] * 7
    meetup = first + timedelta(gap)
    # correct for 'last' and 'teenth'
    if meetup.month != month or (which == "teenth" and meetup.day > 19):
        meetup -= timedelta(7)
    return meetup
    
