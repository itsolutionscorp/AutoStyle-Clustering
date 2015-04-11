#!/usr/bin/python

class Bob:
  def isquestion(self, said):
    if said.endswith("?"):
      return True
    else:
      return False

  def issilence(self, said):
    if said=="":
      return True
    else:
      return False

  def isshout(self, said):
    if said==said.upper():
      return True
    else:
      return False

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
  
