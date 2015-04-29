#!/usr/bin/python3

class Bob:
  def hey(self, a):
    if len(a.strip()) == 0:
      return "Fine. Be that way!"
    elif a.isupper():
      return "Woah, chill out!"
    elif a[-1] == "?":
      return "Sure."
    else:
      return "Whatever."
