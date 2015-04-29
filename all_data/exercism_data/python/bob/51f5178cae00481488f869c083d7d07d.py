class Bob(object):

  class Phrase:
    def __init__(self, text):
      self.text = text

    def style(self):
      if self.is_silence():
        return 'silence' 
      if self.is_shouting():
        return 'shouting' 
      if self.is_question():
        return 'question' 
      return 'regular'

    def is_silence(self):
      return self.text is None or self.text.strip() == ''

    def is_shouting(self):
      return self.text.isupper()

    def is_question(self):
      return self.text.endswith('?')

  responses = {
    'silence': 'Fine. Be that way.',
    'shouting': 'Woah, chill out!',
    'question': 'Sure.'
    }

  def hey(self, text):
    phrase = self.Phrase(text)
    return self.responses.get(phrase.style(), 'Whatever.')
