class Bob():
  def hey(self, msg):
    if msg is None or self._blank(msg):
      return 'Fine. Be that way.'
    elif self._yell(msg):
      return 'Woah, chill out!'
    elif self._question(msg):
      return 'Sure.'
    else:
      return 'Whatever.'

  def _blank(self, msg):
    return msg.strip() == ''

  def _question(self, msg):
    return len(msg) >= 1 and msg[-1] == '?'

  def _yell(self, msg):
    return msg.upper() == msg
