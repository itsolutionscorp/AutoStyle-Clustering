from datetime import date


def meetup_day(year, month, weekday, desc):

    desc_list = ['1st', '2nd', '3rd', '4th']
    days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
            'Friday', 'Saturday', 'Sunday']

    # Special case: 'last'.
    if desc == 'last':
        # Sets the date to the last day of the given month
        check_date = date.fromordinal(date(year, month+1, 1).toordinal() - 1)
        # Iterate backwards through the days until a matching day is found
        while days[check_date.weekday()] != weekday:
            check_date = date.fromordinal(check_date.toordinal() - 1)

    # General case
    else:
        if desc == 'teenth':
            # Check begins at 13
            check_date = date(year, month, 13)
            # We're looking for the 1st instance
            desc = '1st'
        else:
            # Set the date to the start of month as all days need to be checked
            check_date = date(year, month, 1)

        # If check_date is the target weekday, the target date is:
        # 7 times the index of desc in desc_list
        if days[check_date.weekday()] == weekday:
            check_date = date.fromordinal(check_date.toordinal() +
                                          (7 * desc_list.index(desc)))
        # If check_date is a weekday preceding the target weekday,
        # the target weekday is the difference between the indexes
        # of the weekdays + 7 times index of desc
        elif check_date.weekday() < days.index(weekday):
            diff = days.index(weekday) - days.index(days[check_date.weekday()])
            check_date = date.fromordinal(check_date.toordinal() + diff +
                                          (7 * desc_list.index(desc)))
        # Else the target weekday is 7 - the difference between
        # indexes + 7 times index of desc
        else:
            diff = days.index(days[check_date.weekday()]) - days.index(weekday)
            check_date = date.fromordinal(check_date.toordinal() + (7-diff) +
                                          (7 * desc_list.index(desc)))

    return check_date
