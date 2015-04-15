# Bob

def hey(input):
  if not input.strip():
    return "Fine. Be that way!"
  if input.isupper():
    return "Whoa, chill out!"
  if input.endswith("?"):
    return "Sure."
  return "Whatever."
