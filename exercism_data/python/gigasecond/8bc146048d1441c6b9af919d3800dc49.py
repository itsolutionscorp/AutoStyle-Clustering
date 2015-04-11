from datetime import timedelta
gigasecond = timedelta(seconds=1000000000) # hey, no one said I couldn't use timedelta

def add_gigasecond(date):
    return date + gigasecond
