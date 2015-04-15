from datetime import date
import calendar

def meetup_day(year, month, day_of_week, when):
    cal = calendar.monthcalendar(year, month)
    week_map = {"1st" : 1, "2nd" : 8, "3rd" : 15, "teenth" : 13, "4th" : 22, "last" : calendar.monthrange(year,month)[1] - 6 }
    for x in cal:
        if x[getattr(calendar, day_of_week.upper())] >= week_map[when]:
            return date(year, month, x[getattr(calendar, day_of_week.upper())])
