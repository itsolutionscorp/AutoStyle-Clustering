#!/usr/bin/env python
# -*- coding: utf-8 -*-
from datetime import date
from calendar import monthrange

weekday_dict = {"MONDAY": 0,
                "TUESDAY": 1,
                "WEDNESDAY": 2,
                "THURSDAY": 3,
                "FRIDAY": 4,
                "SATURDAY": 5,
                "SUNDAY": 6}

def get_day_range(year, month, boundary):
    if boundary == "1st":
        return (1, 7)
    if boundary == "2nd":
        return (8, 14)
    if boundary == "3rd":
        return (15, 21)
    if boundary == "4th":
        return (22, 28)
    if boundary == "teenth":
        return (13, 19)
    if boundary == "last":
        days_in_month = monthrange(year, month)[1]
        return (days_in_month - 6, days_in_month)


def meetup_day(year, month, weekday, boundary):
    # Get the start and end days to search.
    start_day, end_day = get_day_range(year, month, boundary)
    # Get the datetime value for the target weekday.
    target_weekday = weekday_dict[weekday.upper()]

    for day in range(start_day, end_day+1):
        # Search the day range until the correct weekday is hit and return that date.
        if date(year,month,day).weekday() == target_weekday:
            return date(year,month,day)
