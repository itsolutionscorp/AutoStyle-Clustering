class Bob:
  def hey(self, what):
    if self.silent(what):
      return "Fine. Be that way!"
    elif self.yelling(what):
      return "Woah, chill out!"
    elif self.question(what):
      return "Sure."
    else:
      return "Whatever."

  def yelling(self, what):
    if any(c.isalpha() for c in what):
      return what == what.upper()
    return False
  
  def question(self, what):
    return what.endswith("?")

  def silent(self, what):
    return what.strip() == ''
