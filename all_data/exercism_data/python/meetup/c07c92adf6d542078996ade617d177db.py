import calendar, datetime


def meetup_day(year, month, day, which_day):
    days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    selector = {'1st': 1, '2nd': 2, '3rd': 3, '4th': 4, 'last': 5}
    teenths = range(13, 20)
    sentry = 0
    which_day = selector.get(which_day, 'teenth')  # dict.get takes a second arg that will be the default

    cal = calendar.Calendar()
    cal_tups = [y for x in cal.monthdays2calendar(year, month) for y in x]  # flatten the calendar matrix

    for day_tup in cal_tups:
        month_day, week_day = day_tup
        week_day = days[week_day]  # index days to get the string equivalent

        if month_day == 0: continue  # skip days that aren't in the month

        # which_day is only a string when 'teenth' was passed in
        if isinstance(which_day, str) and week_day == day and month_day in teenths:
            return datetime.date(year, month, month_day)

        if week_day == day: sentry += 1
        if sentry == which_day: return datetime.date(year, month, month_day)
