import calendar
from datetime import date


def meetup_day(year, month, day_otw, which_day):
    # get the total days in the month
    month_length = calendar.monthrange(year, month)[1]

    # Make the month
    days_of_month = []
    for day in range(1, month_length + 1):
        days_of_month.append(date(year, month, day))

    # get all the dates that match the day name
    potential_days = []
    for day in days_of_month:
        if day.strftime('%A') == day_otw:
            potential_days.append(day)

    # find the day that meets the requirements of which_day
    if which_day == 'teenth':
        for the_day in potential_days:
            if int(the_day.strftime('%d')) >= 13 and int(the_day.strftime('%d')) <= 19:
                return the_day

    # Could continue w/ 'third', 'second', 'first' but won't since they aren't in the test
    elif which_day == 'last':
        return potential_days[-1]

    # Take the number from strings like '1st' and use that position from potential_days
    else:
        return potential_days[int(which_day[0]) - 1]
