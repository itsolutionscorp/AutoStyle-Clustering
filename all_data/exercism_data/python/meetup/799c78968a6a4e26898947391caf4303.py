from datetime import date, timedelta
import re


def meetup_day(year, month, day, day_type):
    day_dict = {'Monday': 0,
           'Tuesday': 1,
           'Wednesday': 2,
           'Thursday': 3,
           'Friday': 4,
           'Saturday': 5,
           'Sunday': 6}
    day_one = first_of_the_month(year, month)
    while day_one.weekday() != day_dict[day]:
        day_one += timedelta(days=1)
    day_list = [day_one]
    while (day_one + timedelta(days=7)).month == month:
        day_one += timedelta(days=7)
        day_list.append(day_one)
    if re.match('[0-9]', day_type[0]):
        return day_list[int(day_type[0]) - 1]
    if day_type == 'last':
        return day_list[-1]
    if day_type == 'teenth':
        for teenth_day in day_list:
            if teenth_day.day in range(13, 19):
                return teenth_day


def first_of_the_month(year, month):
    return date(year, month, 1)


if __name__ == '__main__':
    print meetup_day(2013, 5, 'Tuesday', '1st')
