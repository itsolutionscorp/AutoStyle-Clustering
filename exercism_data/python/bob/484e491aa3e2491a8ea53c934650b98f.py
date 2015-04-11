import re

def hey(what):
  letters = re.search(r'[a-zA-Z]', what)  # Finds any letter

  # As long as there's just whitespace or tabs
  if not what.strip():
    return "Fine. Be that way!"
  # Without 'letters' check, will return chill out block
  if not letters and what[-1] != "?":
    return "Whatever."
  # Works as long as there's at least one letter
  if what == what.upper() and letters:
    return "Whoa, chill out!"
  # Simple enough, since all queries will end with a '?'
  if what.strip()[-1] == "?":
    return "Sure."
  return "Whatever."
