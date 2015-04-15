"""
meetup - a module for finding good days to meet up.
"""

from calendar import Calendar
from datetime import date


# --- Category Testing Functions ---

def first(year, month, day):
    return 1 <= day[0] <= 7


def second(year, month, day):
    return  8 <= day[0] <= 14


def third(year, month, day):
    return 15 <= day[0] <= 21


def fourth(year, month, day):
    return 22 <= day[0] <= 28


def last(year, month, day):
    last_day = max(Calendar().itermonthdays(year, month))
    return last_day - day[0] < 7


def teenth(year, month, day):
    return 13 <= day[0] <= 19


# --- Primary Function ---

def meetup_day(year, month, day_name, category):
    """
    Find a day matching the criteria given by the arguments.
    """

    # Translation from English day names to day of the week numbers.
    days_of_the_week = {"Monday": 0,
                        "Tuesday": 1,
                        "Wednesday": 2,
                        "Thursday": 3,
                        "Friday": 4,
                        "Saturday": 5, 
                        "Sunday": 6}

    # Dictionary of category testing functions.
    conditions = {"1st": first,
                  "2nd": second,
                  "3rd": third,
                  "4th": fourth,
                  "last": last,
                  "teenth": teenth}

    # Iterate through all the days of the month for a match.
    num_day_of_the_week = days_of_the_week[day_name]
    condition = conditions[category]
    for day in Calendar().itermonthdays2(year, month):
        if condition(year, month, day):
            if day[1] == num_day_of_the_week:
                return date(year, month, day[0])
