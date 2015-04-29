import re
def hey(msg):
  if not msg.strip():
    return "Fine. Be that way!"
  elif msg.isupper():
    return "Whoa, chill out!"
  elif msg[-1:] == u'?':
    return "Sure."
  else:
    return "Whatever."
    
