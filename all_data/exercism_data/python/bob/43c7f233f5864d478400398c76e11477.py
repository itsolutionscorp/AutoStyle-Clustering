class Bob:

  def hey(self, arg):
    arg = arg.strip()

    if not arg:
      return "Fine. Be that way!"

    if arg.isupper():
      return "Whoa, chill out!"

    if arg.endswith("?"):
      return "Sure."

    return "Whatever."
