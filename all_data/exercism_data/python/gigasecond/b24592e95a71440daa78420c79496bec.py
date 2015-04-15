# Gigasecond - Python Exercism Exercise

from datetime import date
from datetime import timedelta


def add_gigasecond(birth_date):
    ''' Calculates date for gigasecond anniversary '''   
    giga_s = timedelta(seconds=10**9)
    giga_versary = birth_date+giga_s
    return giga_versary

#print add_gigasecond(date(1982, 12, 26))
