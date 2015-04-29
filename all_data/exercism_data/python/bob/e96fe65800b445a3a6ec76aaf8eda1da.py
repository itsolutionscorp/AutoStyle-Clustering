# -*- coding: utf-8 -*-
import re

class Phrase:
  def __init__(self, text):
    self.text = text.strip()
  
  def is_question(self):
    return self.text[-1] == '?'
  
  def is_yelling(self):
    return self.__contains_uppercase_letters() and \
        not self.__contains_lowercase_letters()
  
  def is_silence(self):
    return re.match('^\s*$', self.text)
  
  def __contains_uppercase_letters(self):
    return re.search(u'[A-ZÜ]', self.text, re.UNICODE)
    
  def __contains_lowercase_letters(self):
    return re.search(u'[a-zä]', self.text, re.UNICODE)

  
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
