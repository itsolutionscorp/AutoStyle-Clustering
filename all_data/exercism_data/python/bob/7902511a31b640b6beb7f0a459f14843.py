# -*- coding: utf-8 -*-

import re

def hey(msg):
  if msg.isupper(): # Yelling
    return 'Whoa, chill out!'
  elif len(msg) > 0 and msg[-1] == '?': # Asking a question
    return 'Sure.'
  elif len(msg) == 0 or re.match("^\s+$", msg): # If you don't say anything
    return 'Fine. Be that way!'
  else:
    return 'Whatever.' # Everything else
