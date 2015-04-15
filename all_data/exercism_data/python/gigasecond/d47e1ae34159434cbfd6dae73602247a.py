"""
Finds out when someone will celebrate their 1 gigasecond anniversary.
Written by Bede Kelly for Exercism.
"""

__author__ = "Bede Kelly"

from datetime import timedelta

def add_gigasecond(given_date):
	"""Returns the date one gigasecond later than the given date."""
	return given_date + timedelta(seconds=10**9)
