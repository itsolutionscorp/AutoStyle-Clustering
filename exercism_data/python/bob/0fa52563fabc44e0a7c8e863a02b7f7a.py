class Bob(object):

  @staticmethod
  def hey(message):
    language = Language(message)

    if language.is_silence():
      return "Fine. Be that way!"
    elif language.is_aggressive():
      return "Woah, chill out!"
    elif language.is_question():
      return "Sure."
    else:
      return "Whatever."

class Language(object):

  def __init__(self, message):
    self.message = message or ""

  def is_silence(self):
    return not self.message.strip()

  def is_aggressive(self):
    return self.message.isupper()

  def is_question(self):
    return self.message.endswith('?')
