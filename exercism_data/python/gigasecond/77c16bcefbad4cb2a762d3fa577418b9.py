import datetime

def add_gigasecond(date):
    g_sec_in_days = 10**9/60/60/24
    gigaversary = date + datetime.timedelta(days=g_sec_in_days)
    print gigaversary
    return gigaversary
