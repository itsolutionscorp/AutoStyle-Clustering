from datetime import timedelta

""" Return date 1 Gigasecond from input date d
"""
def add_gigasecond(d):
    # Add 1 Gigasecond (10**9 seconds) to date d
    return d + timedelta(seconds = 10**9)
