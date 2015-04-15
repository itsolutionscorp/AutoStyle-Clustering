import re

class Bob():
  def hey(self, message):
    if self.is_silence(message):
      return "Fine. Be that way!"
    elif self.is_shouting(message):
      return "Woah, chill out!"
    elif self.is_question(message):
      return "Sure."
    else:
      return "Whatever."

  def is_question(self, message):
    return message[-1] == '?'

  def is_silence(self, message):
    return message == None or re.match("^\s*$", message)

  def is_shouting(self, message):
    return message == message.upper()
