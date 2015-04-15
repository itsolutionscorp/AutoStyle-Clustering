class Bob:
  def hey(self, phrase):
    if self.is_a_question(phrase):
      return "Sure."
    elif self.is_yelling(phrase):
      return "Woah, chill out!"
    elif self.is_not_saying_anything(phrase):
      return "Fine. Be that way!"
    else:
      return "Whatever."

  def is_a_question(self, phrase):
    return phrase.endswith("?")

  def is_yelling(self, phrase):
    return phrase == phrase.upper()

  def is_not_saying_anything(self, phrase):
    return phrase.strip() == ""
