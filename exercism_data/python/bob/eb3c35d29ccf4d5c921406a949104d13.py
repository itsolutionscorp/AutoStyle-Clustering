class Bob:
  """Bob the manager"""

  def hey(self, message):
    """Respond to a message"""

    if self._shouting(message):
      return "Woah, chill out!"
    elif self._question(message):
      return "Sure."
    elif self._silence(message):
      return "Fine. Be that way!"
    else:
      return "Whatever."

  def _shouting(self, message):
    return message.isupper()

  def _question(self, message):
    return message.endswith("?")

  def _silence(self, message):
    return len(message.strip()) == 0
