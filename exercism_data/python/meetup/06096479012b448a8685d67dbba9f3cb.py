import datetime
import calendar

def distance(target_day, initial):
        # Find the distance between the target index and the index of the first of the month.
        dist = target_day - initial

        # If the target index occurs before the first of the month add 7 to the distance to find the
        # distance to when it first occurs.
        if dist < 0:
            dist += 7

        return dist

def meetup_day(year, month, week_day, suffix):
    day_list = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    
    if suffix == 'teenth':
        # Year = year
        # Month = month
        # Day = 13 plus the distance() between the 13th day index and the target index.
        return datetime.date(year, month, 13 + distance(day_list.index(week_day), calendar.weekday(year, month, 13)))

    if suffix == 'last':
        # Year = year
        # Month = month
        # Day = The last day of the month minus the distance() between the last day's index and the target index.
        return datetime.date(year, month, calendar.monthrange(year,month)[1] - distance(day_list.index(week_day), calendar.weekday(year, month, calendar.monthrange(year,month)[1])))

    if suffix[0].isdigit():
        # Year = year
        # Month = month
        # Day = The first plus the distance() between the first's index and the target index, then plus 7 for each week.
        return datetime.date(year, month, 1 + distance(day_list.index(week_day), calendar.weekday(year, month, 1)) + 7 * (int(suffix[0])-1))
