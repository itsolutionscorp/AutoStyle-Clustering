#!/usr/bin/env python

def hey(what):
  if (len(what) == 0) or (not any(c.isalnum() for c in what)):
    return 'Fine. Be that way!'
  if (what.upper() == what) and any(c.isalpha() for c in what):
    return 'Whoa, chill out!'
  if (what.upper() != what or any(c.isdigit() for c in what)) and what[-1] == "?":
    return 'Sure.'
  return 'Whatever.'
