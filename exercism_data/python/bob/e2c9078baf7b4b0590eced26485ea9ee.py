class Bob:

  class Phrase:
    def __init__(self, text):
      self.text = text

    def style(self):
      if self.isSilence():
        return 'silence' 
      elif self.isShouting():
        return 'shouting' 
      elif self.isQuestion():
        return 'question' 
      else:
        return 'regular'

    def isSilence(self):
      return self.text is None or self.text.strip() == ''

    def isShouting(self):
      return self.text.upper() == self.text

    def isQuestion(self):
      return self.text[-1] == '?'

  responses = {
    'silence': 'Fine. Be that way.',
    'shouting': 'Woah, chill out!',
    'question': 'Sure.'
    }

  def hey(self, text):
    phrase = self.Phrase(text)
    return self.responses.get(phrase.style(), 'Whatever.')
