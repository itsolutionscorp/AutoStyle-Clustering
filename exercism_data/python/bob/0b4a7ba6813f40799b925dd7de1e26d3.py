class Bob(object):
  def hey(self, text):
    statement = _Statement(text)
    
    if text is None or statement.is_silence():
      return 'Fine. Be that way!'
    
    if statement.is_shouting():
      return 'Woah, chill out!'
    
    if statement.is_question():
      return 'Sure.'
    
    return 'Whatever.'


class _Statement(object):
  def __init__(self, text):
    self._text = text or " "
  
  def is_silence(self):
    return self._text.isspace()
  
  def is_question(self):
    return self._text.endswith("?")
  
  def is_shouting(self):
    return self._text.isupper()
