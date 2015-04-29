class Message(object):
   def __init__(self, message):
      self._message = message

   def nonverbal(self):
      return self._message is None or self._message.strip() == ''

   def yelled(self):
      return self._message.isupper()

   def question(self):
      return self._message.endswith('?')

class Bob(object):
   def hey(self, msg):
      message = Message(msg)
      if message.nonverbal():
         return 'Fine. Be that way!'
      if message.yelled():
         return 'Woah, chill out!'
      if message.question():
         return 'Sure.'
      return 'Whatever.'
