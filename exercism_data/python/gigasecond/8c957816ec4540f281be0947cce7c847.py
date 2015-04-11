from datetime import timedelta

def add_gigasecond(birth_date):
    seconds_in_a_day = 24 * 60 * 60
    days_to_gigasecond = 10**9 / seconds_in_a_day

    return birth_date + timedelta(days=days_to_gigasecond)
