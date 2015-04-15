from datetime import timedelta, date

def meetup_day(year, month, weekday, which):
    weekdays = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
    weeks = {"1st" : 0,
             "2nd" : 1,
             "3rd" : 2,
             "4th" : 3,
             "5th" : 4,
             "teenth" : 2,
             "last" : 4
             }
    first = date(year, month, 1)
    gap = (weekdays.index(weekday) - first.weekday()) % 7   +   weeks[which] * 7
    meetup = first + timedelta(gap)
    if meetup.month != month or (which == "teenth" and meetup.day > 19):
        meetup -= timedelta(7)
    return meetup
    
