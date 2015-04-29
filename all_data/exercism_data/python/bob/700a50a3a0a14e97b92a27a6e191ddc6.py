class Bob:
  def hey(self, message):
    phrase = Phrase(message)
    if   phrase.silence():  return 'Fine. Be that way.'
    elif phrase.shout():    return 'Woah, chill out!'
    elif phrase.question(): return 'Sure.'
    else:                   return 'Whatever.'


class Phrase:
  def __init__(self, message):
    self.phrase = message

  def silence(self):
    return self.phrase is None or self.phrase.strip() == ''

  def question(self):
    return self.phrase[-1] == '?'

  def shout(self):
    return self.phrase == self.phrase.upper()
