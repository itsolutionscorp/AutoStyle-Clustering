class Bob(object):
  def hey(self, message):
    voice = VoiceAnalysis(message)

    if voice.quiet():
      return "Fine. Be that way."

    elif voice.is_shout():
      return "Woah, chill out!"

    elif voice.is_question():
      return "Sure."

    else:
     return "Whatever."


class VoiceAnalysis(object):
  def __init__(self, speech):
    self.speech = speech

  def is_quiet(self):
    return bool(self.speech)

  def is_shout(self):
    return self.speech.isupper()

  def is_question(self):
    return self.speech.endswith("?")
