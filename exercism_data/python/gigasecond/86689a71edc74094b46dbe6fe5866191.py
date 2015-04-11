#!/usr/bin/env python
from datetime import timedelta
# 1 gigasecond is 10**9 seconds
def add_gigasecond(bday):
	return bday + timedelta(seconds=10**9)
