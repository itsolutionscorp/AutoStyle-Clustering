class Bob():
    def hey(self,message):
      import re
      if not message or not len(message.strip(' ')):
          return 'Fine. Be that way!'
      elif message.upper() == message and re.search('[A-Z]+', message):
          return 'Woah, chill out!'
      elif re.search('^.*\?$', message):
          return 'Sure.'
      else:
          return 'Whatever.'
