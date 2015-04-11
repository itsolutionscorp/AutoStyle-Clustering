def hey(what):
  # As long as there's just whitespace or tabs
  if not what.strip():
    return "Fine. Be that way!"
  # Works as long as there's at least one letter
  if what.isupper():
    return "Whoa, chill out!"
    # Simple enough, since all queries will end with a '?'
  if what.strip()[-1] == "?":
    return "Sure."
  return "Whatever."
