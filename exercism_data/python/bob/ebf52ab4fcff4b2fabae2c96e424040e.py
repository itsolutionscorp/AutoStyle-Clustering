class Bob:

  def isQuestion(self, prompt):
    if self.isShout(prompt):
      return False
    else:
      return prompt.endswith('?')

  def isBlank(self, prompt):
    return not prompt or prompt.isspace()

  def isShout(self, prompt):
    return prompt.isupper()

  def hey(self, prompt):
    if self.isBlank(prompt):
      return 'Fine. Be that way!'
    elif self.isQuestion(prompt):
      return 'Sure.'
    elif self.isShout(prompt):
      return 'Whoa, chill out!'
    else:
      return 'Whatever.'
