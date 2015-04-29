class Bob():
  def hey(self, question):
    if len(question) == 0:
      return "Fine. Be that way."
    elif question[-1] == '?':
      return "Sure."
    elif question.upper() == question:
      return "Woah, chill out!"
    else:
      return "Whatever."
