# -*- coding: utf-8 -*-

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
  msg_error = 'Speak human.'

  # Sanitization
  if isinstance(greeting_raw, str):
    greeting = greeting_raw.strip()
  else:
    return msg_error

  # Logic
  if not greeting:
    return msg_fine
  elif greeting.isupper():
    return msg_chillout
  elif greeting.endswith('?'):
    return msg_sure
  else:
    return msg_whatever
