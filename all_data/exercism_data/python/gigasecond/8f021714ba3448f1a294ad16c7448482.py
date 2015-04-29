def add_gigasecond(date):
    timestamp = int(date.strftime("%s"))  # UNIX timestamp
    return date.fromtimestamp(timestamp + 10**9)
