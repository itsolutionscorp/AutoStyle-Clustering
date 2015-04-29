class Bob():
  def hey(self, message):
    if self.__is_silence(message):
      return "Fine. Be that way!"
    elif self.__is_shouting(message):
      return "Woah, chill out!"
    elif self.__is_question(message):
      return "Sure."
    else:
      return "Whatever."

  def __is_question(self, message):
    return message.endswith("?")

  def __is_silence(self, message):
    return not message or message.isspace()

  def __is_shouting(self, message):
    return message.isupper()
