from datetime import date
from calendar import monthrange

def parseWeekday(pass_day):
    if pass_day[0].isdigit():
        return int(pass_day[0])-1
    elif pass_day == 'last':
        return -1
    return 10  # Arbitrary value greater than any week number for the case of the 'teenth' value

def parseDay(day):
    # Returns an integer value that corresponds with a day of the week
    weekDays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
    return weekDays[day]


def meetup_day(year, month, weekday, weeknum):

    # Parse the week number from a string to an integer
    weeknum = parseWeekday(weeknum)
    # Parse the week day from a string to an integer
    weekday = parseDay(weekday)

    # Determine first day and total days of specific month
    first_day, total_days = monthrange(year, month)

    # Determine date where weekday is first encountered
    firstmatch = (weekday - first_day) % 7 + 1

    # In the case of the 'teenth' day find week in which teenth day occurs
    if weeknum == 10:
        # Create range of all the dates of specific weekday in month
        x = xrange(firstmatch, total_days+1, 7)
        # Find week in which 'teenth' day occurs
        for i in range(len(x)):
            if 13 <= x[i] <= 19:
                # Return result formatted as date
                return date(year, month, xrange(firstmatch, total_days+1, 7)[i])
            
    # Return result formatted as date
    return date(year, month, xrange(firstmatch, total_days+1, 7)[weeknum])
