#!/usr/bin/python

class Bob:
  def isquestion(self, said):
    return said.endswith("?")

  def issilence(self, said):
    return said==""

  def isshout(self, said):
    return said==said.upper()

  def hey(self, heard):
    if self.isquestion(heard):
      return "Sure."
    elif self.issilence(heard):
      return "Fine. Be that way."
    elif self.isshout(heard):
      return "Woah, chill out!"
    else:
      return "Whatever."

if __name__ == '__main__':
  bob=Bob()
  bob.hey("wha?")
  
