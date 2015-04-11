def is_silent(message):
  return "" == message

def is_shouting(message):
  return message.isupper()

def is_question(message):
  return "?" == message[-1]

class Bob(object):
  def hey(self, message):
    if is_silent(message):
      return "Fine, be that way."
    if is_shouting(message):
      return "Woah, chill out!"
    if is_question(message):
      return "Sure."
    return "Whatever."
