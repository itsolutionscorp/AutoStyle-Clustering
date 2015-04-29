"""
Exercism.io
Python Gigasecond

Author: jdcampo
"""

from datetime import timedelta

def add_gigasecond(birthday):
    return birthday + timedelta(seconds=10**9)
