class Bob():
  @staticmethod
  def hey(sentence):
    if Bob.is_silence(sentence):
      return "Fine. Be that way."
    if Bob.is_question(sentence):
      return "Sure."
    if Bob.is_yelling(sentence):
      return "Woah, chill out!"

    return "Whatever."

  @staticmethod
  def is_silence(sentence):
    return not sentence

  @staticmethod
  def is_question(sentence):
    return sentence.endswith('?')

  @staticmethod
  def is_yelling(sentence):
    return sentence.upper() == sentence
