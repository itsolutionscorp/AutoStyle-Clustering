#
# captainnurple's code for the Python meetup exercise.
#
import datetime, calendar

# Accounts for: 
#   1st, first
#   2nd, second
#   3rd, third
#   4th, fourth
#   5th, fifth      <- yes a day can appear 5 times
#
# and special cases:
#   teenth, last
#
# So, given YEAR and MONTH, provide DATE from a WEEKDAY and SPECIFIER

# meetup_day takes a year, month, day and specifier
# and returns the specific date that it falls on. Per above specs.
def meetup_day(year, month, day, specifier):
    # First turn desired day into an int. Monday is 0
    # And specifier into an int as well
    weekdayInt = intForWeekday(day)
    specifierInt = intForSpecifier(year, month, weekdayInt, specifier) # y, m, wI needed for 'last' case

    # Instantiate calendar object
    cal = calendar.Calendar()
    occurrence = 0

    # Now iterate through the days in year/month using itermonthdays2,
    # which returns a touple of [date num, weekday num]
    # 0 checks are because itermonthdays2 always returns full weeks, so date num will
    # be zero if it falls outside calendar month

    for date in cal.itermonthdays2(year, month):
        if (date[0] != 0 and date[1] == weekdayInt): # Ensures we're actually inside the month then checks weekday
            # Check for special case 'teenth' (specifierInt == 0)
            if (specifierInt == 0 and date[0] < 20 and date[0] > 12):
                return datetime.date(year, month, date[0])
            # No 'teenth', so now check for desired occurrence
            occurrence += 1
            if (occurrence == specifierInt):
                return datetime.date(year, month, date[0])


def intForWeekday(day):
    if (day == 'Monday'):
        return 0
    elif (day == 'Tuesday'):
        return 1
    elif (day == 'Wednesday'):
        return 2
    elif (day == 'Thursday'):
        return 3
    elif (day == 'Friday'):
        return 4
    elif (day == 'Saturday'):
        return 5
    elif (day == 'Sunday'):
        return 6
    else:
        print('intForWeekday ERROR. Input weekday not matched.')
        return -1

def intForSpecifier(year, month, weekdayInt, specifier):
    if (specifier == '1st') | (specifier == 'first'):
        return 1
    elif (specifier == '2nd') | (specifier == 'second'):
        return 2
    elif (specifier == '3rd') | (specifier == 'third'):
        return 3
    elif (specifier == '4th') | (specifier == 'fourth'):
        return 4
    elif (specifier == '5th') | (specifier == 'fifth'):
        return 5
    elif (specifier == 'teenth'):
        return 0
    elif (specifier == 'last'):
        return specifierForLast(year, month, weekdayInt)
    else:
        print('intForSpecifier ERROR. Input specifier not matched')
        return -1

def specifierForLast(year, month, weekdayInt):
    cal = calendar.Calendar()
    counter = 0
    for date in cal.itermonthdays2(year, month):
        if (date[0] != 0 and date[1] == weekdayInt):
            counter += 1
    return counter
