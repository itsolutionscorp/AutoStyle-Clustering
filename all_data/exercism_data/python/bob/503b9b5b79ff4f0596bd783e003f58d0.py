class Bob:

  @staticmethod
  def hey(message):
    language = Language(message)

    if language.is_silence():
      return "Fine. Be that way!"
    elif language.is_aggresive():
      return "Woah, chill out!"
    elif language.is_question():
      return "Sure."
    else:
      return "Whatever."

class Language(object):

  def __init__(self, message):
    self.message = message

  def is_silence(self):
    return self.message is None or self.message.strip() == ""

  def is_aggresive(self):
    return self.message.isupper()

  def is_question(self):
    return self.message.endswith('?')
