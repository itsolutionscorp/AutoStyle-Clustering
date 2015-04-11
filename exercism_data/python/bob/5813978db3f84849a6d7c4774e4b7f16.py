class Bob(object):
  def is_yelling(self,string):
    return string.isupper()

  def is_question(self,string):
    return string.endswith('?')

  def is_empty(self,string):
    return len(string) == 0 or string.isspace()

  def hey(self,string):
    if self.is_empty(string):
      return 'Fine. Be that way!'
    elif self.is_yelling(string):
      return 'Woah, chill out!'
    elif self.is_question(string):
      return 'Sure.'
    else:
      return "Whatever."
