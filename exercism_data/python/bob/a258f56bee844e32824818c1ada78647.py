from __future__ import unicode_literals

def hey(msg):
  if len(msg.strip()) == 0:
    return 'Fine. Be that way!'
  elif msg.isupper():
    return 'Whoa, chill out!'
  elif msg[-1] == '?':
    return 'Sure.'
  return 'Whatever.'
