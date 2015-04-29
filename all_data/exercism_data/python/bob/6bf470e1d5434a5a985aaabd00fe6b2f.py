class Bob():
    def hey(self,message):
      import re
      if not message or len(message.strip(' ')) == 0:
          return 'Fine. Be that way!'
      elif re.match('^[A-Z]+$', re.sub('[^a-zA-Z]', '', message)) != None:
          return 'Woah, chill out!'
      elif re.search('^.*\?$', message) != None:
          return 'Sure.'
      else:
          return 'Whatever.'
