from calendar import Calendar
from itertools import islice

teenth_set = (13, 14, 15, 16, 17, 18, 19)
desc_map = {
    "1st": 1,
    "2nd": 2,
    "3rd": 3,
    "4th": 4,
    "last": -1,
    "teenth": -1
}
day_map = {
    "Monday": 0,
    "Tuesday": 1,
    "Wednesday": 2,
    "Thursday": 3,
    "Friday": 4,
    "Saturday": 5,
    "Sunday": 6,
}
"""
calculates the meetup day
"""
def meetup_day(year, month, day, desc):
    # check for unknown desc
    if desc in desc_map:
        idx = desc_map[desc]
    else:
        return None

    #create calendar iterator
    iterator = Calendar().itermonthdates(year, month)

    # slice iterator for only days we care about, jump by week
    dates = islice(iterator, day_map[day], None, 7)

    # iterate over dates to find our date
    ret = None
    for date in dates:
        if date.month == month:  # calendar iterator sometimes has old months...
            ret = date
            idx -= 1
            if idx == 0:  # we've reached the nth date
                return ret
            elif date.day in teenth_set and desc == "teenth":  # we've reached the teenth date
                return ret
    return ret  # last
