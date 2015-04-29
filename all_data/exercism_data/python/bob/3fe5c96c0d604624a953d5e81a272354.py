#!/usr/bin/env python

def hey(quote):                      # bring in the string from bob_test.py
  if not quote.strip():              # make sure string is not empty
    return "Fine. Be that way!"
  elif quote.isupper():              # determine if whole string is upper case
    return "Whoa, chill out!"
  elif quote[-1] == "?":             # check the final character for "?"
    return "Sure."
  else:
    return "Whatever."
