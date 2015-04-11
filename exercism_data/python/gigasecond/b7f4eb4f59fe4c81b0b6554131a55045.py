import datetime 
__author__ = 'greg'

G = datetime.timedelta(seconds=10**9) 

def add_gigasecond(birthday):
    return birthday + G
