from datetime import date, timedelta

def add_gigasecond(start_date):
    # mins in day 24 * 60 = 1440
    # secs in day 1440 * 60 = 86400
    
    # a billion seconds is 11574 days
    # 1000000000 / 86400 = 11574
    gigasec_days = 11574
    
    # use timedelta to add days to start_date
    gigasec_aniv = start_date + timedelta(days=gigasec_days)
    return gigasec_aniv
