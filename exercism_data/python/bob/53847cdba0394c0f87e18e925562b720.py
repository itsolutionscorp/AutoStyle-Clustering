class Message(object):
   def __init__(self, message):
      self._message = message

   def is_nonverbal(self):
      return self._message is None or self._message.strip() == ''

   def is_yelled(self):
      return self._message.isupper()

   def is_question(self):
      return self._message.endswith('?')

class Bob(object):
   def hey(self, msg):
      message = Message(msg)
      if message.is_nonverbal():
         return 'Fine. Be that way!'
      if message.is_yelled():
         return 'Woah, chill out!'
      if message.is_question():
         return 'Sure.'
      return 'Whatever.'
