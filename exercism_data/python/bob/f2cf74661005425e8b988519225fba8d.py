# -*- coding: utf-8 -*-
class Phrase:
  def __init__(self, text):
    self.text = text.strip()
  
  def is_question(self):
    return self.text.endswith('?')
  
  def is_yelling(self):
    return self.text.isupper()
  
  def is_silence(self):
    return self.text == ''
  
def hey(text):
  phrase = Phrase(text)

  if phrase.is_silence():
    return 'Fine. Be that way!'
  elif phrase.is_yelling():
    return 'Whoa, chill out!'
  elif phrase.is_question():
    return 'Sure.'
  else:
    return 'Whatever.'
