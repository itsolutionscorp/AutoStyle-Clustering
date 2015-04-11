from datetime import date

def add_gigasecond(datum):
    return datum + datetime.timedelta(seconds=1e9)
