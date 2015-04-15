class Bob:
  def hey(self, sentence):
    if self.is_silent(sentence):
      return "Fine, be that way."  
    elif self.is_shouting(sentence):
      return "Woah, chill out!"
    elif self.is_question(sentence):
      return "Sure."
    else:
      return "Whatever."

  def is_question(self, sentence):
    return sentence[-1] == "?"

  def is_shouting(self, sentence):
    return sentence == str.upper(sentence)

  def is_silent(self, sentence):
    return not sentence
