class Bob:
  def hey(self, message):
    if not message:
      return "Fine. Be that way."
    if self.__screaming(message):
      return "Woah, chill out!"
    if self.__question(message):
      return "Sure."
    return "Whatever."

  def __screaming(self, message):
    return message.upper() == message

  def __question(self, message):
    return message[len(message) - 1] == "?"
