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
                counter = instance_nums[instance]
                if cal[0][weekday_nums[weekday]] == 0 and counter + 1 < len(cal):
                    day = cal[counter + 1][weekday_nums[weekday]]
                elif cal[0][weekday_nums[weekday]] > 0 and counter < len(cal):
                    day = cal[counter][weekday_nums[weekday]]  #rather than iterating, this should do fewer calculations
                else:
                    print 'Error: instance of weekday not found'
            elif instance == 'teenth':
                for week in range(1, 3):  # A '-teenth' day always falls in the second to fourth week of the month.
                    if cal[week][weekday_nums[weekday]] in range(13, 20):
                        day = cal[week][weekday_nums[weekday]]
            elif instance == 'last':
                for j in range(len(cal) - 1, len(cal) - 2, -1):  # Always falls in last or penultimate week.
                    if cal[j][weekday_nums[weekday]] > 0:
                        day = cal[j][weekday_nums[weekday]]
                        break
            else:
                print 'Error: instance of weekday not found'
        else:
            print 'Error: weekday input incorrect'

        if day == 0:
            print 'Error: Date not found'
        else:
            return date(year, month, day)
    else:
        print 'Month must be between 1 and 12'
