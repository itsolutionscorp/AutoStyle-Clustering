import re

class Bob:
  def hey(self, phrase):
    phrase = Phrase(phrase)

    if phrase.is_yelling():
      return "Woah, chill out!"
    elif phrase.is_a_question():
      return "Sure."
    elif phrase.is_not_saying_anything():
      return "Fine. Be that way!"
    else:
      return "Whatever."

class Phrase:
  def __init__(self, text):
    self.text = text

  def is_a_question(self):
    return self.text.endswith("?")

  def is_yelling(self):
    return self.text.isupper()

  def is_not_saying_anything(self):
    return not self.text.strip()
