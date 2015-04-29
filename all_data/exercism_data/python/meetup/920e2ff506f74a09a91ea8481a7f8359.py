#!/usr/bin/python
import datetime
import calendar
def meetup_day(year, month, weekday, occurrence):
    """ returns the day of the month that a meeting should be if the meeting is
    in year and month, on the occurrence time weekday occurs"""
    first_day = determine_first_day(year, month, occurrence)

    weekdayDict = dict(Monday = 0, Tuesday  = 1, Wednesday= 2, Thursday = 3,
                       Friday   = 4, Saturday = 5, Sunday   = 6)
    weekday_num = weekdayDict[weekday]
    first_weekday_num = datetime.date(year, month, first_day).weekday()

    day_of_meeting = calc_day_of_meeting(first_day, weekday_num,
                                         first_weekday_num)


    return datetime.date(year, month, day_of_meeting)

def determine_first_day(year, month, occurrence):
    """ returns the appropriate starting day of the month for each occurrence
    indicator """
    firstDayDict = {'1st':1, '2nd':8, '3rd':15, '4th':22, 'teenth':13,
                    'last':calendar.monthrange(year, month)[1]-6}
    return firstDayDict[occurrence]

def calc_day_of_meeting(first_day, weekday_num, first_weekday_num):
    """ calculates the date of the month, given the start date of that
    occurrence week and the day of the week the meeting should be on"""
    if weekday_num >= first_weekday_num:
        return first_day + weekday_num - first_weekday_num
    else:
        return first_day + 7 + weekday_num - first_weekday_num
