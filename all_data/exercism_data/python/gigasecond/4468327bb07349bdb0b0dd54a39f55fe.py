#!/usr/bin/env python
# -*- coding: utf-8 -*-

# Submission file for the python gigasecond_test exercise.
#
# v1: First iteration

from datetime import date

def add_gigasecond(mydate):
  '''
  :Calculate 1Gs Date from mydate
  :
  : 864000 seconds in 1 day
  : 10**9 is 1G
  '''
  return mydate.fromordinal(mydate.toordinal()+((10**9)/86400))
