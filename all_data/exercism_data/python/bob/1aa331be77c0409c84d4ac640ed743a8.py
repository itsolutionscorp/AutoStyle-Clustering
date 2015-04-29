import re

class Bob(object):

  def hey(self, string):
    if self.isSilence(string): 
      return 'Fine. Be that way!'
    elif self.isShout(string):
      return 'Woah, chill out!'
    elif self.isQuestion(string):
      return 'Sure.'
    else:
      return 'Whatever.'

  def isSilence(self, string):
    if re.search(r"\S+", string):
      return False
    else:
      return True

  def isQuestion(self, string):
    return string[-1] == '?'

  def isShout(self, string):
    return string.upper() == string and re.search(r"[A-Z]+", string)
