class Message: 
  def __init__(self, content):
    self.__content = content

  def is_shouting(self):
    return self.__content.isupper()

  def is_question(self):
    return self.__content.endswith("?")

  def is_silence(self):
    return not self.__content

class Bob:
  def hey(self, content):
    message = Message(content)
    if message.is_silence():
      return "Fine, be that way."
    elif message.is_shouting():
      return "Woah, chill out!"
    elif message.is_question():
      return "Sure."
    else:  
      return "Whatever."
