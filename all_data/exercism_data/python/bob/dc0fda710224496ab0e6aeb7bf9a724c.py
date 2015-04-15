import re

class Bob:
  def hey(self, message):

    if message.strip() == "":
      return 'Fine. Be that way!'
    elif message == message.upper() and re.search("[a-z]", message, re.IGNORECASE):
      return 'Woah, chill out!'
    elif message[-1] == "?":
      return 'Sure.'

    return 'Whatever.'
