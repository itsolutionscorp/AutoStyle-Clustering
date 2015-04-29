from datetime import date
from calendar import monthcalendar

def meetup_day(year, month, day_of_week, modifier):
    # this dictionary will be used to translate the day of the week
    day_dict = {
        'Monday': 0,
        'Tuesday': 1,
        'Wednesday': 2,
        'Thursday': 3,
        'Friday': 4,
        'Saturday': 5,
        'Sunday': 6
    }

    # create a list of all the dates in the month on the given day of the week
    # monthcalendar() generates a matrix, and we extract all dates from the appropriate column
    day_list = [monthcalendar(year, month)[x][day_dict[day_of_week]]
                for x in range(0, len(monthcalendar(year, month)))]

    # remove place-holder zeroes leftover from the original matrix
    [day_list.remove(d) for d in day_list if not d]

    # case logic
    if modifier == 'teenth':
        for d in day_list:
            if 12 < d < 20:
                return date(year, month, d)
    else:
        # 1st -> 1, 2nd -> 2, ..., last -> -1
        return date(year, month, day_list[int(modifier[0]) - 1 if modifier[0].isdigit() else -1])
