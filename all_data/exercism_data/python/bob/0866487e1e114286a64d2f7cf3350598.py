def hey(msg):
   if msg.isupper():
      return 'Woah, chill out!'
   if msg.endswith('?'):
      return 'Sure.'
   if not msg.strip():
      return 'Fine. Be that way!'
   return 'Whatever.'