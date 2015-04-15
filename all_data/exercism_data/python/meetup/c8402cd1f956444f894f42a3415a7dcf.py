__author__ = 'Eric'

from datetime import date
import calendar


def meetup_day(year, month, weekday, occurrence):

    # Parse 'weekday' input into integer value
    weekdays = {'Monday': 0,
                'Tuesday': 1,
                'Wednesday': 2,
                'Thursday': 3,
                'Friday': 4,
                'Saturday': 5,
                'Sunday': 6}

    weekday = weekdays[weekday]

    # map '#st' input to integers
    occurrences = {'1st': 0,
                   '2nd': 1,
                   '3rd': 2,
                   '4th': 3}

    # Handle '1st, 2nd, etc' input
    if occurrence in occurrences.keys():

        # Find out what weekday the first day of the month is
        first_wday = calendar.monthrange(year, month)[0]

        # Using the above, find out what the first day of the month is
        # that matches the desired weekday.
        if weekday < first_wday:
            start = 1 + 7 - abs(weekday - first_wday)
        else:
            start = 1 + weekday - first_wday

        # Now that we know the first 'weekday' of the month, find the
        # n-th occurrence and return it
        return date(year, month, start + occurrences[occurrence]*7)

    # Handle 'teenth' input
    if occurrence == 'teenth':

        # iterate through 'teenth' days until you match the weekday
        for i in range (13, 19):
            if date(year, month, i).weekday() == weekday:
                return date(year, month, i)

    if occurrence == 'last':
        # find last day of the month
        last_day = calendar.monthrange(year, month)[1]

        # Start with the last day of the month, and
        # iterate backwards until you match the requested
        # day of the week
        while True:
            if date(year, month, last_day).weekday() == weekday:
                return date(year, month, last_day)
            else:
                last_day -= 1
