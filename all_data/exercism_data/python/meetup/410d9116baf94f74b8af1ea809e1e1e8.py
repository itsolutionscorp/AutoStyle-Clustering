from datetime import date
import calendar
from collections import defaultdict

days = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday']
date_map = {}


def init_date_map():
    global date_map
    date_map = defaultdict(list)


def meetup_day(year, month, day_of_week, criteria):
    map_month(year, month)
    day = find_day_meeting_criteria(day_of_week, criteria)
    return date(year, month, day)


def map_month(year, month):
    init_date_map()
    start_day, num_days = calendar.monthrange(year, month)
    for i in range(1, num_days + 1):
        day_of_week = (start_day + i - 1) % 7
        date_map[day_of_week].append(i)


def teenth(day_index):
    for date_candidate in date_map[day_index]:
        if date_candidate > 12:
            return date_candidate


def find_day_meeting_criteria(day_of_week, criteria):
    day_index = days.index(day_of_week.lower())

    return {
        '1st': date_map[day_index][0],
        '2nd': date_map[day_index][1],
        '3rd': date_map[day_index][2],
        '4th': date_map[day_index][3],
        'last': date_map[day_index][-1],
        'teenth': teenth(day_index)
    }.get(criteria, None)
