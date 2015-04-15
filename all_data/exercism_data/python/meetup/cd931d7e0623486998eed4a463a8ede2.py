import datetime, calendar

WEEK_MAP = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday"
]

def meetup_day(year, month, day_name, when_english):
    # datetime.weekday() mapping
    desired_weekday = WEEK_MAP.index(day_name)

    # First to last day of month as a range()
    validated_range = calendar.monthrange(year, month);

    count = 0

    # Indices need incremented by 1 for accuracy - brute-force algorithm
    for day_index in range(1, validated_range[1] + 1):
        if datetime.date(year, month, day_index).weekday() is desired_weekday:
            count += 1

            if when_english is "teenth" and day_index in range(12, 20):
                break
            elif when_english is "1st" and count is 1:
                break
            elif when_english is "2nd" and count is 2:
                break
            elif when_english is "3rd" and count is 3:
                break
            elif when_english is "4th" and count is 4:
                break
            elif when_english is "last" and day_index > validated_range[1] - 6:
                break

    return datetime.date(year, month, day_index)
