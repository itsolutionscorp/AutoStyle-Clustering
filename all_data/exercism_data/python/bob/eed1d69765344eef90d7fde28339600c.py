class Bob():
  def hey(self, msg):
    if self._blank(msg):
      return 'Fine. Be that way.'
    elif self._yell(msg):
      return 'Woah, chill out!'
    elif self._question(msg):
      return 'Sure.'
    else:
      return 'Whatever.'

  def _blank(self, msg):
    return msg is None or msg.strip() == ''

  def _question(self, msg):
    return msg.endswith('?')

  def _yell(self, msg):
    return msg.isupper()
