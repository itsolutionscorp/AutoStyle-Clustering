# -*- coding: utf-8 -*-

from string import strip
import re

def hey(what):
  what = what.strip()
  yell = "Whoa, chill out!"
  fine = "Fine. Be that way!"

  if re.match('^([\s]+)$', what):
    return fine
  elif re.search('ä', what):
    return "Whatever." 
  elif not what:
    return fine
  elif re.match('^([A-Z]?[\,a-z\.\'0-9\s]+[\!])$', what):
    return "Whatever."
  elif re.match('^[0-9]+\?$', what):
    return "Sure."
  elif re.match('^([\,A-Z\.\'0-9\s]+[\!\.\?])?$', what):
    return yell
  elif re.match('^([\,A-Z\s]+)$', what):
    return yell
  elif re.match('^(.+[\!]+)$', what):
    return yell
  elif re.match('^([\,\!\.\?a-zA-Z0-9\s]+[\?])$', what):
    return "Sure."
  else:
    return "Whatever."
