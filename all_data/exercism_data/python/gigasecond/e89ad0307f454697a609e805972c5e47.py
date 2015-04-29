from datetime import datetime, timedelta

def add_gigasecond(dob):
    '''Given a dob returns when a person would turn 1bn seconds'''
    return dob + timedelta(seconds=10**9)
