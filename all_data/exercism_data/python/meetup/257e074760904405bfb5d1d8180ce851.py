'''
from datetime import date
from calendar import monthrange

import string
__author__ = "Bill Davis"

#0 is Monday, 6 is Sunday


def meetup_day(year, month, day, modifier):
    week_days = ['Monday', 'Tuesday', 'Wednesday',
                 'Thursday', 'Friday', 'Saturday', 'Sunday']

    day_in = week_days.index(day)
    first_day, number_days = monthrange(year, month)

    # defaults to modifier '1st'

    day_of_month = day_in - first_day + 1
    if day_in < first_day:
        day_of_month += 7

    if modifier == '2nd':
        day_of_month += 7
    elif modifier == '3rd':
        day_of_month += 14
    elif modifier == '4th':
        day_of_month += 21
    elif modifier == 'last':
        day_of_month += 28
        if day_of_month > number_days:
            day_of_month -= 7
    elif modifier == 'teenth':
        day_of_month += 14
        if day_of_month > 19:
            day_of_month -= 7

    return date(year, month, day_of_month)

'''
from datetime import date
from calendar import monthrange

import string
__author__ = "Bill Davis"

#0 is Monday, 6 is Sunday


def meetup_day(year, month, day, modifier):
    week_days = ['Monday', 'Tuesday', 'Wednesday',
                 'Thursday', 'Friday', 'Saturday', 'Sunday']

    day_in = week_days.index(day)
    first_day, number_days = monthrange(year, month)

    def last(day):
        day += 28
        if day > number_days:
            day -= 7
        return day

    def teenth(day):
        day += 14
        if day > 19:
            day -= 7
        return day

    def first(day):
        return day

    def second(day):
        return day + 7

    def third(day):
        return day + 14

    def fourth(day):
        return day + 21

    # Maps modifier to function test
    modifiers = {'teenth': teenth,
                 'last': last,
                 '1st': first,
                 '2nd': second,
                 '3rd': third,
                 '4th': fourth,
                 }

    earliest_day = day_in - first_day + 1
    if day_in < first_day:
        earliest_day += 7

    return date(year, month, modifiers[modifier](earliest_day))
