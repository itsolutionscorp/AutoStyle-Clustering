# -*- coding: utf-8 -*-

def hey(sentence):
  if sentence.isupper():
    return 'Woah, chill out!'

  elif sentence.endswith('?'):
    return 'Sure.'

  elif sentence.strip() == '':
    return 'Fine. Be that way!'

  return 'Whatever.'
