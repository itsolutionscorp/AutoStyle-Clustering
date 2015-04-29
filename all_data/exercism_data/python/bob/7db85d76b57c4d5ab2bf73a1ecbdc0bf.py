#!/usr/bin/python

import sys

# Gather our code in a main() function

class Bob:
  def __init__(self):
    pass

  def hey(self, heard):
    if heard.endswith("?"):
      return "Sure."
    elif heard=="":
      return "Fine. Be that way."
    elif heard==heard.upper():
      return "Woah, chill out!"
    else:
      return "Whatever."
    # Command line args are in sys.argv[1], sys.argv[2] ..


if __name__ == '__main__':
  bob=Bob()
  bob.hey("wha?")

  
