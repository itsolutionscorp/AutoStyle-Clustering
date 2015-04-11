class Bob:
  def hey(self, s):
    if self.__isempty(s):
      return 'Fine. Be that way.'

    if self.__isshout(s):
      return 'Woah, chill out!'

    if self.__isquestion(s):
      return 'Sure.'

    return 'Whatever.'

  def __isempty(self, s):
    return s == None or s.strip() == ''

  def __isshout(self, s):
    return s.isupper()

  def __isquestion(self, s):
    return s.endswith('?')
