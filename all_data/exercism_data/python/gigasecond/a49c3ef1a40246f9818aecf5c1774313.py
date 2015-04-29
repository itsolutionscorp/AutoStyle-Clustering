# 1 Gigasecond = 11574 days

import datetime

gs_days = 11574

def add_gigasecond(age):
    return age + datetime.timedelta(gs_days)
