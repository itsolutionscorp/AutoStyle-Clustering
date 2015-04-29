# -*- coding: utf-8 -*-
import sys

def hey(greeting_raw):
  """Questions return 'Sure.'
  All caps (yelling) return 'Whoa, chill out!'
  Empty greetings return 'Fine. Be that way!'
  All other greetings return 'Whatever.'"""

  # Responses
  msg_fine = 'Fine. Be that way!'
  msg_chillout = 'Whoa, chill out!'
  msg_sure = 'Sure.'
  msg_whatever = 'Whatever.'
  msg_error = 'I don\'t think you\'re speaking human. You gave me a: '

  # Sanitization
  if isinstance(greeting_raw, str):
    greeting = greeting_raw.strip()
  else:
    return "%s%s" % (msg_error, type(greeting_raw))

  # Logic
  if not greeting:
    return msg_fine
  elif greeting.isupper():
    return msg_chillout
  elif greeting.endswith('?'):
    return msg_sure
  else:
    return msg_whatever
