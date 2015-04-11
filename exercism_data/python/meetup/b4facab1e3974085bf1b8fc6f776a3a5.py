from datetime import date, timedelta

def meetup_day(year, month, target_weekday, position):
    day = date(year, month, 1)
    possible = []

    while True:
        weekday = day.strftime("%A")
        if weekday == target_weekday:
            possible.append(day)
        day = day + timedelta(days = 1)
        if day.month != month:
            break

    _positions = {"1st": 0, "2nd": 1, "3rd": 2, "4th": 3}
    if not _positions.get(position) is None:
        return possible[_positions[position]]

    elif position == "teenth":
        if possible[1].day - 13 < 0:
           return possible[2]
        else:
           return possible[1]

    else:
        return possible[-1]
