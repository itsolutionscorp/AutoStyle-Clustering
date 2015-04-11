import re

class Bob:
  """Bob"""
  def hey(self, greeting):
    silence = lambda greeting: greeting.strip() == "";
    shouting = lambda greeting: greeting.upper() == greeting;
    question = lambda greeting: greeting.endswith("?");

    if silence(greeting or ""):
      return "Fine. Be that way!"
    elif shouting(greeting):
      return "Woah, chill out!"
    elif question(greeting):
      return "Sure."
    else:
      return "Whatever."
