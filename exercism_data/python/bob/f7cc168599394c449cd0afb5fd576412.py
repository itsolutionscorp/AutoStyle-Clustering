#

class Bob(object):
  def __init__(self):
    pass

  def is_yelling(self,string):
    if len(string) == 0:
      return False
    elif string.isupper() and not string.isdigit():
      return True
    else:
      return False

  def is_question(self,string):
    if len(string) == 0:
      return False
    elif string[len(string)-1] == "?":
      return True
    else:
      return False

  def is_empty(self,string):
    if len(string) == 0 or string.isspace():
      return True
    else:
      return False

  def hey(self,string):
    if self.is_empty(string):
      return 'Fine. Be that way!'
    elif self.is_yelling(string):
      return 'Woah, chill out!'
    elif self.is_question(string):
      return 'Sure.'
    else:
      return "Whatever."
