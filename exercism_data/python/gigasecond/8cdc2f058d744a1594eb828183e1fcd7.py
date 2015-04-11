from datetime import date

epoch_date = date(1970, 1, 1)
gigasecond = 10 ** 9

def add_gigasecond(original_date):
    timestamp = (original_date - epoch_date).total_seconds()
    return date.fromtimestamp(timestamp + gigasecond)
