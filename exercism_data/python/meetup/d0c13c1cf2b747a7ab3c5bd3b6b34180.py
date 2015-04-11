from datetime import date

weekdays = ( "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" )
def get_weekday_number(day):
    return weekdays.index(day)

def meetup_day(year, month, weekday, week):
    weekday = get_weekday_number(weekday)
    start_of_month = date(year, month, 1)

    day_offset = weekday - start_of_month.weekday()
    if day_offset < 0:
        day_offset += 7
    day = 1 + day_offset

    try:
        week_of_month = int(week[0])
    except ValueError:
        week_of_month = -1

    if week_of_month != -1:
        week_offset = 7 * (week_of_month - 1)
        meeting = date(year, month, day + week_offset)
    elif week == "teenth":
        if day + 14 < 19:
            day += 14
        else:
            day += 7
        meeting = date(year, month, day)
    elif week == "last":
        try:
            meeting = date(year, month, day + 28) # try 5 weeks ahead
        except ValueError:
            meeting = date(year, month, day + 21) # else, 4 weeks ahead

    return meeting
