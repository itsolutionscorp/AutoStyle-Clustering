class Bob():
  @staticmethod
  def hey(sentence):
    if sentence == "":
      return "Fine. Be that way."
    elif sentence.endswith("?"):
      return "Sure."
    elif sentence.upper() == sentence:
      return "Woah, chill out!"
    else:
      return "Whatever."
