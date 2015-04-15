from datetime import date
import calendar


def meetup_day(year, month, day, desc):
    ''' Returns the date of the meet up '''

    # Dictionary with all the days
    days = {
        'Monday': 0,
        'Tuesday': 1,
        'Wednesday': 2,
        'Thursday': 3,
        'Friday': 4,
        'Saturday': 5,
        'Sunday': 6,
    }

    # Creating a calendar
    cal = calendar.Calendar()

    if desc.__contains__('1') or desc == 'first':
        for i, a_day in cal.itermonthdays2(year, month):
            if i != 0 and a_day == days[day]:
                meet_up_day = i
                break

    elif desc.__contains__('2') or desc == 'second':
        for i, a_day in cal.itermonthdays2(year, month):
            # Don't check the first week
            if i > 7 and a_day == days[day]:
                meet_up_day = i
                break
    elif desc.__contains__('3') or desc == 'third':
        for i, a_day in cal.itermonthdays2(year, month):
            # Don't the first and second week
            if i > 14 and a_day == days[day]:
                meet_up_day = i
                break
    elif desc.__contains__('4') or desc == 'fourth':
        for i, a_day in cal.itermonthdays2(year, month):
            # Check only the last week
            if i > 21 and a_day == days[day]:
                meet_up_day = i
                break
    elif desc == 'last':
        for i, a_day in reversed(list(cal.itermonthdays2(year, month))):
            if i != 0 and a_day == days[day]:
                meet_up_day = i
                break
    elif desc == 'teenth':
        for i, a_day in cal.itermonthdays2(year, month):
            if i > 12 and a_day == days[day]:
                meet_up_day = i
                break
    else:
        print desc, " is not supported."


    return date(year, month, meet_up_day)
