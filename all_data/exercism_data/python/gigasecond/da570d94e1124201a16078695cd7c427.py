__author__ = 'citypulse-dp'
from datetime import timedelta


def add_gigasecond(birthdate):
    gigasecond = 10**9
    giga_birthday = birthdate + timedelta(seconds=gigasecond)
    return giga_birthday
