class Bob:
  """implements a teenager Bob"""
  def hey(self, message):
    if self.talker_is_silent(message):
      return 'Fine. Be that way.'
    elif self.talker_is_shouting(message):
      return 'Woah, chill out!'
    elif self.talker_is_asking(message):
      return 'Sure.'
    else:
      return "Whatever."

  def is_blank(self, string):
    return len(string) == 0 or string.isspace()
  
  def talker_is_silent(self, string):
    return string is None or self.is_blank(string)
  
  def talker_is_asking(self, string):
    return string.endswith('?')
  
  def talker_is_shouting(self, string):
    return string.isupper()
