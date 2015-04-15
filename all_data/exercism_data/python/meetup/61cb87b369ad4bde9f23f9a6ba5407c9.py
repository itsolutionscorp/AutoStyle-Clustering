from datetime import date, timedelta

class MeetupDayException(Exception):
    """Raised when meetup day does not exist"""
    pass

def meetup_day(year, month, weekday, flags):
    meetup_date = date(year, month, 1)
    weekdays = dict(zip('Monday Tuesday Wednesday Thursday Friday Saturday Sunday'.split(), range(7)))
    if flags.endswith("teenth"): 
        teens_start = 11
        teens_end = 20
        meetup_date += timedelta(days = teens_start) 
        while meetup_date < meetup_date + timedelta(days = teens_end):
              meetup_date += timedelta(days=1)
              if meetup_date.weekday() == weekdays.get(weekday):
                 break
    elif flags == "last":
        meetup_date += timedelta(days=31)
        while meetup_date < meetup_date + timedelta(days=23):
             meetup_date -= timedelta(days=1)
             if meetup_date.weekday() == weekdays.get(weekday):
                 break
    else:
        week = int(flags[0])
        if week == 5:
            raise MeetupDayException()
        meetup_date += timedelta(days=week*7-1)
        while meetup_date < meetup_date + timedelta(days=8):
             meetup_date += timedelta(days=1)
             if meetup_date.weekday() == weekdays.get(weekday):
                 break
        meetup_date -= timedelta(days=7)
    return meetup_date
