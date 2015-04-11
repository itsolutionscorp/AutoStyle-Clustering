from datetime import datetime

def add_gigasecond(dob):
    '''Given a dob returns when a person would turn 1bn seconds'''
    gs = datetime.timestamp(dob) + 10**9
    return datetime.fromtimestamp(gs)
