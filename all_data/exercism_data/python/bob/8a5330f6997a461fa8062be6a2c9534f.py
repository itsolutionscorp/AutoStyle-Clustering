def is_silent(x):
  if x != None:
     x.strip()
     if len(x) == 0:
        return True
     elif x.isspace():
        return True 
  elif x == None:
     return True

def is_shouting(x):
  return x.isupper()

def is_question(x):
  if len(x) != 0:
    return x[-1] == "?"

class Bob(object):
  def hey(self, x):
    if is_silent(x):
      return "Fine. Be that way!"
    if is_shouting(x):
      return "Woah, chill out!"
    if is_question(x):
      return "Sure."
    else:
     return "Whatever."
