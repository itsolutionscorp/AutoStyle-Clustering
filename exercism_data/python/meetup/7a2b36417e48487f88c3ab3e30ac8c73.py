from datetime import date

def meetup_day(year, month, weekday, desc):

    # Maps the inputs to usable values.
    desc_dict = {'1st':1, '2nd':2, '3rd':3, '4th':4}
    days = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}

    # Special case 1: 'last'.
    if desc == 'last':
        # Sets the date to the last day of the given month
        check_date = date.fromordinal(date(year, month+1, 1).toordinal() - 1)
        # Iterate backwards through the days until a matching day is found
        while check_date.weekday() != days[weekday]:
            check_date = date.fromordinal(check_date.toordinal() - 1)

    # Special case 2: 'teenth'.
    elif desc == 'teenth':
        # Checking need only begin at 13
        check_date = date(year, month, 13)
        # Iterate forwards until matching day is found.
        while check_date.weekday() != days[weekday]:
            check_date = date.fromordinal(check_date.toordinal() + 1)

    # General case
    else:
        # Set the date to the start of the month as all days need to be checked
        check_date = date(year, month, 1)
        # Keeps count of how many times we have seen our target weekday
        weekday_matches = 0
        # Loops only through the current month, to avoid any infinite loops
        while check_date.month == month:
            # Every time we see the target weekday, we increment weekday_matches...
            if check_date.weekday() == days[weekday]:
                weekday_matches += 1
            # ...and check if we've found the right one
            if weekday_matches == desc_dict[desc]:
                break
            else:
                # If we haven't, increment the date and loop around
                check_date = date.fromordinal(check_date.toordinal() + 1)

    return check_date
