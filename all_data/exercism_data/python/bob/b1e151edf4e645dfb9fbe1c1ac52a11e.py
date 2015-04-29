# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  response_msgs = ['Whatever.', 'Whoa, chill out!', 'Sure.', 'Fine. Be that way!']
  if what.isupper(): return response_msgs[1] #Shouting
  if not what.strip(): return response_msgs[3] #Silent
  if what.strip()[-1]=='?': return response_msgs[2] #Question
  return response_msgs[0] #indifference
