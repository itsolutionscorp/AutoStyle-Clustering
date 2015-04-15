import re 

class Bob:

  def hey(self, input):
    if self.silence_check(input):
      return "Fine. Be that way!"
    elif self.scream_check(input):
      return "Woah, chill out!"
    elif self.question_check(input):
      return "Sure."
    else:
      return "Whatever." 

  def silence_check(self, input):
    return input.rstrip() == ""

  def scream_check(self, input):
    return input == input.upper() and re.search('[A-Z]', input)

  def question_check(self, input):
    return input.rstrip()[-1] == "?"
