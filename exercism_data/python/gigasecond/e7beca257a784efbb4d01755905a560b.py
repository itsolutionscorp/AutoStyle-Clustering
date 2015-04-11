#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from datetime import date, timedelta

# Submission file for the python gigasecond_test exercise.
#
# v3: Use global constant for gigasecond to avoid calculating it over
#     and over again.
# v2: Use integer 10**9 instead of 1e9, which is a float.
# v1: Using datetime.timedelta. No error checks are done.

GIGA_SECOND = 10**9

def add_gigasecond(birthday):
	"""
	Return the date that someone will celebrate their 1 Gs anniversary.
	"""

	anniversary = birthday + timedelta(seconds=GIGA_SECOND)

	return anniversary
