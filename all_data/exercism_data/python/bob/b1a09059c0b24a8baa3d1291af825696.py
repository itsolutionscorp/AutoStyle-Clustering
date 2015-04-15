class Bob:

  def hey(self, sentence):
    if self.is_silent(sentence):
      return "Fine. Be that way!"
    elif self.is_yelling(sentence):
      return "Woah, chill out!"
    elif self.is_question(sentence):
      return "Sure."
    else:
      return "Whatever."

  def is_silent(self, sentence):
    return sentence is None or sentence.strip() == ''
  def is_yelling(self, sentence):
    return sentence.isupper()
  def is_question(self, sentence):
    return sentence.endswith('?')

def hey(sentence):
  return Bob().hey(sentence)
