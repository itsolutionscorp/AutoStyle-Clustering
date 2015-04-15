class Bob:

  def hey(self, speech_heard):
    if self.is_silent(speech_heard):
      return "Fine. Be that way!"
    elif self.is_screaming(speech_heard):
      return "Woah, chill out!"
    elif self.is_question(speech_heard):
      return "Sure."
    else:
      return "Whatever." 

  def is_silent(self, speech_heard):
    return speech_heard.rstrip() == ""

  def is_screaming(self, speech_heard):
    return speech_heard.isupper()

  def is_question(self, speech_heard):
    return speech_heard.rstrip()[-1] == "?"
