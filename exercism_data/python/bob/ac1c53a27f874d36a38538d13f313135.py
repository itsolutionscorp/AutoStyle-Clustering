class Bob(object):
  def hey(self, message):
    voice = VoiceAnalysis(message)

    if voice.is_quiet():
      return "Fine. Be that way."
    if voice.is_shout():
      return "Woah, chill out!"
    if voice.is_question():
      return "Sure."

    return "Whatever."


class VoiceAnalysis(object):
  def __init__(self, speech):
    self.speech = speech

  def is_quiet(self):
    return not self.speech

  def is_shout(self):
    return self.speech.isupper()

  def is_question(self):
    return self.speech.endswith("?")
