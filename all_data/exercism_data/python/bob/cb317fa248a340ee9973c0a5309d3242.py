class Bob:
  def hey(self, message):
    if self._is_silence(message):
      return "Fine. Be that way."
    if self._is_scream(message):
      return "Woah, chill out!"
    if self._is_question(message):
      return "Sure."
    return "Whatever."

  def _is_scream(self, message):
    return message.upper() == message

  def _is_question(self, message):
    return message.endswith("?")

  def _is_silence(self, message):
    return not message
