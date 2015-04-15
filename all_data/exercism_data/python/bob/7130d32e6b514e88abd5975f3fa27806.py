def hey(message):

  # silence
  if message.strip() == "":
    return "Fine. Be that way!"

  # yelling (takes precedence over questions)
  if message.isupper():
    return "Woah, chill out!"

  # questions
  if message.endswith("?"):
    return "Sure."

  return "Whatever."
