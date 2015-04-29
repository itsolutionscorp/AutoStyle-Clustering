import re
def hey(msg):
  msg = msg.strip()
  if not msg:
    return "Fine. Be that way!"
  elif msg.isupper():
    return "Whoa, chill out!"
  elif msg[-1:] == u'?':
    return "Sure."
  else:
    return "Whatever."
    
