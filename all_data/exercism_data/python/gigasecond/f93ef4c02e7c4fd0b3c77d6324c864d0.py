#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from datetime import date, timedelta

# Submission file for the python gigasecond_test exercise.
#
# v2: Use integer 10**9 instead of 1e9, which is a float.
# v1: Using datetime.timedelta. No error checks are done.

def add_gigasecond(birthday):
	"""
	Return the date that someone will celebrate their 1 Gs anniversary.
	"""

	anniversary = birthday + timedelta(seconds=10**9)

	return anniversary
