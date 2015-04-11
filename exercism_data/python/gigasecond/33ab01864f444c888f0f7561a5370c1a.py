#!/usr/bin/env python
# -*- coding: utf-8 -*-

# Submission file for the python gigasecond_test exercise.
#
# v2: Second iteration based on @sondring's solution using timedelta 
#     resulting in cleaner/clearer code
# v1: First iteration

from datetime import timedelta

def add_gigasecond(d):
  '''
  :Calculate 1Gs Date from d
  '''
  return d + timedelta(0, 10**9)
