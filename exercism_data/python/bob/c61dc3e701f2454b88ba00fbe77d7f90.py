class Bob(object):
  def hey(self, msg):
    msg = msg.strip()
    
    if msg.isupper():
      return 'Woah, chill out!'

    elif msg.endswith('?'):
      return 'Sure.'

    elif msg == '':
      return 'Fine. Be that way!'

    else:
      return 'Whatever.'
