class Bob:
  def hey(self, statement):
    statement = Statement(statement)
    silenceFilter = statement.silence
    questionFilter = statement.question
    shoutingFilter = statement.shouting
    anythingFilter = statement.anything

    if silenceFilter():
      return 'Fine. Be that way.'
    elif questionFilter():
      return 'Sure.'
    elif shoutingFilter():
      return 'Woah, chill out!'
    elif anythingFilter():
      return 'Whatever.'

class Statement(object):
  def __init__(self, statement):
    self.statement = statement

  def silence(self):
    return not self.statement

  def question(self):
    return self.statement.endswith('?')

  def shouting(self):
    return self.statement.isupper()

  def anything(self):
    return True
