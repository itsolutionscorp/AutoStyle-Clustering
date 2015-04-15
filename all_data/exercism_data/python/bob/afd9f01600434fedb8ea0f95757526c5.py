class Bob(object):
  def hey(self, message):
    analysis = VoiceAnalysis(message)

    if(analysis.is_quiet()):
      return "Fine. Be that way."

    elif(analysis.is_shout()):
      return "Woah, chill out!"

    elif(analysis.is_question()):
      return "Sure."

    else:
     return "Whatever."


class VoiceAnalysis(object):
  def __init__(self, speech):
    self.speech = speech

  def is_quiet(self):
    return self.speech == ""

  def is_shout(self):
    return self.speech.isupper()

  def is_question(self):
    return self.speech.endswith("?")
