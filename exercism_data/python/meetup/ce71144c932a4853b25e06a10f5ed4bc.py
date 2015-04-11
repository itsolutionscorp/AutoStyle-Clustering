from datetime import date
import calendar

class MeetupDayException(Exception):
    def __init__(self, arg):
        self.args = arg

def meetup_day(year, month, weekday, week):
    day_num = list(calendar.day_name).index(weekday)
    cal = calendar.monthcalendar(year,month)
    weekdays_in_month = []
    for w in cal:
        weekdays_in_month.append(w[day_num])
    while weekdays_in_month.count(0) != 0:
        weekdays_in_month.remove(0)
    if (week == "1st"):
        return date(year,month,weekdays_in_month[0])
    elif (week == "2nd"):
        return date(year,month,weekdays_in_month[1])
    elif (week == "3rd"):
        return date(year,month,weekdays_in_month[2])
    elif (week == "4th"):
        return date(year,month,weekdays_in_month[3])
    elif (week == "5th"):
        try:
            return date(year,month,weekdays_in_month[4])
        except IndexError:
            raise MeetupDayException("5th week doesn't exist")
    elif (week == "last"):
        return date(year,month,weekdays_in_month[-1])
    elif (week == "teenth"):
        for day in weekdays_in_month:
            if (12<day<20):
                return date(year,month,day)
