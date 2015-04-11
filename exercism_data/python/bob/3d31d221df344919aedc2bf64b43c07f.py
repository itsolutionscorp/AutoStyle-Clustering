class Bob:

  def hey(self, message):
    if message == "" or message == None or message.isspace():
      return 'Fine. Be that way!'
    elif message.isupper():
      return "Woah, chill out!"
    elif "?" ==  message[-1]:
      return "Sure."
    return "Whatever."
