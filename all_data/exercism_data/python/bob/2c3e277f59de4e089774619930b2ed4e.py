import re

def hey(what):
  non_spaces = re.search(r'\S+', what) #Finds any non-whitespace
  letters = re.search(r'[a-zA-Z]', what) #Finds any letter

  if not non_spaces: #As long as there's just whitespace or tabs
    return "Fine. Be that way!"
  if not letters and what[-1] != "?": #Without 'letters' check, will return chill out block
    return "Whatever."
  if what == what.upper() and letters: #Works as long as there's at least one letter
    return "Whoa, chill out!"
  if what[-1] == "?": #Simple enough, since all queries will end with a '?'
    return "Sure."
  return "Whatever."
