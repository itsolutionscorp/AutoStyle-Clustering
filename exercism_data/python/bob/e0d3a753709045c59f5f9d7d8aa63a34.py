class Bob:

  def hey(self, sentence):
    if sentence is None or sentence.strip() == '':
      return "Fine. Be that way!"
    elif sentence.isupper():
      return "Woah, chill out!"
    elif sentence.endswith('?'):
      return "Sure."
    else:
      return "Whatever."
