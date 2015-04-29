import calendar
from datetime import date


def meetup_day(year, month, day_name, ordinal) -> date:
    '''
    Returns a date object
    '''

    # yields an array of arrays of dates arrayed by week
    cal = calendar.monthcalendar(year, month)

    # returns 0 thru 6 for Monday thru Sunday
    day_id = eval("calendar." + day_name.upper())

    # 0 means that day doesn't occur in the month of interest
    possible_days = [i[day_id] for i in cal if i[day_id] != 0]

    if ordinal == "teenth":
        # find the date of possible_days that's a teen
        day = list(filter(lambda x: x in range(12, 20), possible_days))[0]
    else:
        ordinal_dict = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1}
        day = possible_days[ordinal_dict[ordinal]]

    return date(year, month, day)
