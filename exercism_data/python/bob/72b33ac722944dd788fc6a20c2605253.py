class Bob(object):
  
  def hey(self, text):
    message = Message(text)
    if message.is_silent():
      final_response = self._annoyed_reponse()
    elif message.is_yell():
      final_response = self._anxious_response()
    elif message.is_question():
      final_response = self._approval_response()
    else:
      final_response = self._neutral_response()

    return final_response

  def _neutral_response(self):
    return 'Whatever.'

  def _annoyed_reponse(self):
    return 'Fine. Be that way!'

  def _approval_response(self):
    return 'Sure.'

  def _anxious_response(self):
    return 'Woah, chill out!'


class Message(object):

  def __init__(self, text):
    self.text = text

  def is_silent(self):
    return not self.text or self.text.strip() == ""

  def is_yell(self):
    return self.text.isupper()

  def is_question(self):
    return self.text.endswith("?")
    
