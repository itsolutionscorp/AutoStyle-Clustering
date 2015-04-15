#!/usr/bin/env python
# -*- coding: UTF-8 -*-

def hey(s=''):
  # empty string
  if len(s.strip()) == 0:
    return 'Fine. Be that way!'
  
  # non empty and all upper (YELLING)
  if s.isupper():
    return 'Whoa, chill out!'
    
  # is a question
  if s.endswith('?'):
    return 'Sure.'

  # catch all
  return 'Whatever.'
