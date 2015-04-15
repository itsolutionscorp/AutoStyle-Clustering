import calendar
from datetime import date


def meetup_day(year, month, weekday, instance):
    if month in range(1, 13):
        cal = calendar.monthcalendar(year, month)
        weekday_nums = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
        instance_nums = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, '5th': 4}
        day = 0
        if weekday in weekday_nums:
            if instance in instance_nums:
                day = nth_instance_of_weekday_in_month(cal, weekday_nums[weekday], instance_nums[instance])
            elif instance == 'teenth':
                day = teenth_weekday(cal, weekday_nums[weekday])
            elif instance == 'last':
                day = last_weekday(cal, weekday_nums[weekday])
            else:
                raise ValueError('invalid instance input')
        else:
            raise ValueError('invalid weekday input')

        if day == 0:
            raise ValueError('date not found')
        else:
            return date(year, month, day)
    else:
        raise 'invalid month input'

def nth_instance_of_weekday_in_month(calendar, weekday_0_to_6, instance_0_4):
    # check if weekday's first instance is in first or second week. Adapt date accordingly.
    counter = instance_0_4
    if calendar[0][weekday_0_to_6] == 0 and counter + 1 < len(calendar):
        return calendar[counter + 1][weekday_0_to_6]
    elif calendar[0][weekday_0_to_6] > 0 and counter < len(calendar):
        return calendar[counter][weekday_0_to_6]  #rather than iterating, this should do fewer calculations
    else:
        raise 'instance of weekday not found'

def teenth_weekday(calendar, weekday_0_to_6):
    for week in range(1, 3):  # A '-teenth' day always falls in the second to fourth week of the month.
        if calendar[week][weekday_0_to_6] in range(13, 20):
            return calendar[week][weekday_0_to_6]
            break

def last_weekday(calendar, weekday_0_to_6):
    # Always falls in last or penultimate week. Check from back to front.
    for week in range(len(calendar) - 1, len(calendar) - 2, -1):
        if calendar[week][weekday_0_to_6] > 0:
            return calendar[week][weekday_0_to_6]
            break
