from datetime import date

def add_gigasecond(d):
    return date.fromtimestamp(int(d.strftime('%s')) + 10**9)
