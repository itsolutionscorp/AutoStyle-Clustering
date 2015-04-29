class Bob:
   def hey(self, msg):
      if self.message_is_empty(msg):
         return 'Fine. Be that way!'
      elif self.message_is_yelled(msg):
         return 'Woah, chill out!'
      elif self.message_is_a_question(msg):
         return 'Sure.'
      return 'Whatever.'

   def message_is_empty(self, msg):
      return (msg == None) or (msg.strip() == '')

   def message_is_yelled(self, msg):
      ''' Yelling is a message in ALL CAPS. '''
      return msg == msg.upper()

   def message_is_a_question(self, msg):
      ''' A question ends with a question mark. '''
      return msg.endswith('?')
