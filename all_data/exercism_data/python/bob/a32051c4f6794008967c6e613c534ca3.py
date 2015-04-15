class Bob(object):
  """
  Bob is a lackadaisical teenager
  """
  def hey(self, inp):
    """
    This is how Bob responds to statements and questions.
    It's a terrible method name but it's what the test uses

    Args:
      inp (string): input for Bob to respond to

    Returns:
      response (string): Bob's response to the given input
    """
    if self._says_nothing(inp):
      return "Fine. Be that way!"
    elif self._is_shouting(inp):
      return "Woah, chill out!"
    elif self._is_question(inp):
      return "Sure."
    return "Whatever."

  def _says_nothing(self, inp):
    return all([char == " " for char in inp])

  def _is_shouting(self, inp):
    # python has this built in! sweet!
    return inp.isupper()

  def _is_question(self, inp):
    return inp[-1] == "?"
