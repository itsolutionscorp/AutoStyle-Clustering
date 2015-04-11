# Thanks to jordannoble for the advice!

def hey(what):

  what = what.strip()

  if what.isupper():
  	reply = "Whoa, chill out!"
  elif what.endswith('?'):
  	reply = "Sure."
  elif what == "":
  	reply = "Fine. Be that way!"
  else:
  	reply = "Whatever."

  return reply
