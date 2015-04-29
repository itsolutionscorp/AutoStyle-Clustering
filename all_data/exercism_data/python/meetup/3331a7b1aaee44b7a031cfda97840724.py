import calendar
import datetime

day_map = {'Monday': calendar.MONDAY, 'Tuesday': calendar.TUESDAY, 'Wednesday': calendar.WEDNESDAY, 'Thursday':
        calendar.THURSDAY, 'Friday': calendar.FRIDAY, 'Saturday': calendar.SATURDAY, 'Sunday': calendar.SUNDAY}

ordinal_map = {'1st': 0, '2nd': 7, '3rd': 14, '4th': 21}

def day_delta(day_index, first_day_index):
    return ((day_index - first_day_index) % 7)

def meetup_day(year, month, day_str, info):
    month_range = calendar.monthrange(year, month)
    first_day_of_month = month_range[0]
    last_day_of_month = first_day_of_month + month_range[1]
    first_day = datetime.date(year, month, 1)
    last_day = datetime.date(year, month, last_day_of_month - first_day_of_month)
    if info in ordinal_map:
        return first_day + datetime.timedelta(days=ordinal_map[info] + day_delta(day_map[day_str], first_day_of_month))
    if info == 'last':
        return last_day - datetime.timedelta(days=(last_day_of_month - 1 - day_map[day_str]) % 7)
    if info == 'teenth':
        for day in range(13,20):
            if day_map[day_str] - day_map[datetime.date(year, month, day).strftime("%A")] == 0:
                return datetime.date(year, month, day)
    return None
