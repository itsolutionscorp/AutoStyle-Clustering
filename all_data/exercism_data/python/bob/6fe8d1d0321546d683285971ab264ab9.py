class Bob:
  def hey(self, what):
    if self.is_silent(what):
      return "Fine. Be that way!"
    elif self.is_yelling(what):
      return "Woah, chill out!"
    elif self.is_question(what):
      return "Sure."
    else:
      return "Whatever."

  def is_yelling(self, what):
    if any(c.isalpha() for c in what):
      return what == what.upper()
    return False
  
  def is_question(self, what):
    return what.endswith("?")

  def is_silent(self, what):
    return what.strip() == ''
