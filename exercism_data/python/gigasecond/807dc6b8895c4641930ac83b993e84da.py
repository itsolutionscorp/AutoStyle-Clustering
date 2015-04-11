import datetime


def add_gigasecond(date):
    # Calculating constants
    seconds_per_day = 3600 * 24
    days_per_gigasecond = int((10 ** 9) / seconds_per_day)
    remaining_seconds = (10 ** 9) % seconds_per_day

    # Timedelta objects make this easy
    time_difference = datetime.timedelta(days=days_per_gigasecond, seconds=remaining_seconds)

    after_a_gigasecond = date + time_difference

    return after_a_gigasecond
