class Bob(object):

  def __init__(self):
    pass

  def hey(self, msg):
    if type(msg)!=str:
      return 'Fine. Be that way!'
    if msg.isupper():
      return 'Woah, chill out!'
    if msg.endswith('?'):
      return 'Sure.'
    if msg.strip()=='':
      return 'Fine. Be that way!'
    return 'Whatever.'
