from datetime import timedelta

def add_gigasecond(d):
	"""Adds a gigasecond to whatever date is provided.
	A gigasecond is 1 billion seconds.
	"""
	return d + timedelta(0, 1000000000)
