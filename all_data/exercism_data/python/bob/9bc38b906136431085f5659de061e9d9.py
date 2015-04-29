import re
def hey(msg):
  shouting = True
  anyAlpha = False
  for c in msg:
    if c.isalpha():
      anyAlpha = True
      shouting = shouting and c.isupper()
  if len(msg) is 0 or re.match('^\s*$', msg, re.UNICODE):
    return "Fine. Be that way!"
  elif shouting and anyAlpha:
    return "Whoa, chill out!"
  elif msg[-1:] == u'?':
    return "Sure."
  else:
    return "Whatever."
    
