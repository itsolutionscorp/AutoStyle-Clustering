class TeenagerUtils:
  @classmethod
  def isblank(cls, string):
    """class method isblank(cls, string):
    returns true if string is length zero or entirely whitespace
    """
    return len(string) == 0 or string.isspace()
  @classmethod
  def issilent(cls, string):
    """class method issilent(cls, string):
    returns true if string is NoneType or if cls.isblank(string) is true
    """
    return string is None or cls.isblank(string)
  @classmethod
  def isquestion(cls, string):
    """class method isquestion(cls, string):
    returns true if string ends with a '?' character
    """
    return string.endswith('?')
  @classmethod
  def isshouting(cls, string):
    """class method isshouting(cls, string):
    returns true if all alphabetic characters in string are uppercase
    """
    return string.isupper()

T = TeenagerUtils  # shorthand

class Bob:
  """implements a teenager Bob"""
  def hey(self, message):
    """method hey(self, message):
    returns teenager Bob's response to message
    """
    if T.issilent(message):
      return 'Fine. Be that way.'
    elif T.isshouting(message):
      return 'Woah, chill out!'
    elif T.isquestion(message):
      return 'Sure.'
    else:
      return "Whatever."
