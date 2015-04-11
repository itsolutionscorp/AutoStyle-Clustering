from datetime import date
import calendar

def meetup_day(year, month, weekday, which):
    def weekday_to_number(weekday):
        weekday_map = {'Monday':0,
                       'Tuesday':1,
                       'Wednesday':2,
                       'Thursday':3,
                       'Friday':4,
                       'Saturday':5,
                       'Sunday':6}
        return weekday_map[weekday]
    def date_of_next_weekday(year, month, day, weekday):
        given_date = date(year, month, day)
        day_delta = (weekday - given_date.weekday() + 7) % 7
        return date(year, month, day + day_delta)
    def date_of_prev_weekday(year, month, day, weekday):
        given_date = date(year, month, day)
        day_delta = (given_date.weekday() - weekday + 7) % 7
        return date(year, month, day - day_delta)
    def last_day_of_month(year, month):
        return calendar.monthrange(year, month)[1]
    weekday = weekday_to_number(weekday)
    if which == 'last':
        return date_of_prev_weekday(year, month, last_day_of_month(year, month), weekday)
    elif which == 'teenth': # teenth: (13, 19)
        return date_of_next_weekday(year, month, 13, weekday)
    else: # 1st: (1, 7), 2nd: (8, 14) and etc.
        return date_of_next_weekday(year, month, 7 * (int(which[0]) - 1) + 1, weekday)
