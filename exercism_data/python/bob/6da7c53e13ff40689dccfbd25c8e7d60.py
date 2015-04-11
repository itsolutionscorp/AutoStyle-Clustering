class Bob(object):
  
  def hey(self, text):
    message = Message(text)
    if message.is_silent():
      return self.__annoyed_reponse()
    elif message.is_yell():
      return self.__anxious_response()
    elif message.is_question():
      return self.__approval_response()
    else:
      return self.__neutral_response()

  def __neutral_response(self):
    return 'Whatever.'

  def __annoyed_reponse(self):
    return 'Fine. Be that way!'

  def __approval_response(self):
    return 'Sure.'

  def __anxious_response(self):
    return 'Woah, chill out!'


class Message(object):

  def __init__(self, text):
    self.text = text

  def is_silent(self):
    return not self.text or self.text.strip() == ""

  def is_yell(self):
    return str(self.text).isupper()

  def is_question(self):
    return str(self.text).endswith("?")
    
