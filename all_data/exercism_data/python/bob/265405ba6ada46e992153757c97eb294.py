class Bob(object):

  @staticmethod
  def hey(message):
    tone = Tone(message)

    if tone.is_silence():
      return "Fine. Be that way!"
    elif tone.is_aggressive():
      return "Woah, chill out!"
    elif tone.is_question():
      return "Sure."
    else:
      return "Whatever."

class Tone(object):

  def __init__(self, message):
    self.message = message or ""

  def is_silence(self):
    return not self.message.strip()

  def is_aggressive(self):
    return self.message.isupper()

  def is_question(self):
    return self.message.endswith('?')
