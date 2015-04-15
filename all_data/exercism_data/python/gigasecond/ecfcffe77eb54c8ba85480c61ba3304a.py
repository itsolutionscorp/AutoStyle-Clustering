# Imports
from datetime import date
from datetime import timedelta

# Functions
def add_gigasecond(birthdate):
	# Define a gigasecond
	gigasecond = timedelta(seconds=10**9)
	
	# Add a gigasecond to the given birthdate and return it.
	return birthdate + gigasecond
