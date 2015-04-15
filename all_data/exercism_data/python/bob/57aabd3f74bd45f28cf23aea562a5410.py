#!/usr/bin/python
# File Name: bob.py
# Created:   22-09-2014

import sys

def hey(input):
  """ asks hey to and his responses"""
  if input.isupper():
    return 'Whoa, chill out!'
  elif input and input[-1] == '?':
    return 'Sure.'
  elif not input.strip():
    return 'Fine. Be that way!'
  else:
    return 'Whatever.'

def main(argv):
  """main func"""

if __name__ == "__main__":
  sys.exit(main(sys.argv))
