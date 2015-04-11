import datetime


def meetup_day(year, month, day_of_week_name, position):
    days_of_week = [
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    ]
    day_of_week = days_of_week.index(day_of_week_name)

    days_in_month = []

    # Find the days in that month with the correct weekday.
    for i in range(31):
        # Create a date object
        try:
            date = datetime.date(year, month, i + 1)
        except ValueError:
            # We don't want the ValueError dates, so we continue.
            pass

        # Check date object
        if date.weekday() == day_of_week:
            days_in_month.append(date)

    if position == "1st":
        return days_in_month[0]
    elif position == "2nd":
        return days_in_month[1]
    elif position == "3rd":
        return days_in_month[2]
    elif position == "4th":
        return days_in_month[3]
    elif position == "last":
        return days_in_month[-1]
    elif position == "teenth":
        for date in days_in_month:
            if date.day in range(13, 20):
                return date
    else:
        return "Something went wrong."


def name_to_number(day_of_week):

    days = {
        "Monday": 0,
        "Tuesday": 1,
        "Wednesday": 2,
        "Thursday": 3,
        "Friday": 4,
        "Saturday": 5,
        "Sunday": 6
    }

    for day in days:
        if day_of_week == day:
            return days[day]
    else:
        return None
