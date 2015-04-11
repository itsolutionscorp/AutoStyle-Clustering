# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  response_msgs = ['Whatever.', 'Whoa, chill out!', 'Sure.', 'Fine. Be that way!']
  what = what.strip()
  if not what: return response_msgs[3] #Silent
  if what.isupper(): return response_msgs[1] #Shouting
  if what.endswith('?'): return response_msgs[2] #Question
  return response_msgs[0] #indifference
