#!/usr/bin/python

class Bob:
  def hey(self, heard):
    if heard.endswith("?"):
      return "Sure."
    elif heard=="":
      return "Fine. Be that way."
    elif heard==heard.upper():
      return "Woah, chill out!"
    else:
      return "Whatever."

if __name__ == '__main__':
  bob=Bob()
  bob.hey("wha?")
  
