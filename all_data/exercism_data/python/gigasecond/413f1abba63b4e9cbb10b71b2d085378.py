from datetime import date, timedelta

def add_gigasecond(startDate):
    gigaSec = 10 ** 9
    maxSeconds = 3600 * 24 # Maximum seconds allowed by timedelta
    return startDate + timedelta(gigaSec / maxSeconds, gigaSec % maxSeconds)
