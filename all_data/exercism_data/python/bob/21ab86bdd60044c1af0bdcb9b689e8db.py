class Bob:
  def hey(this, text):
    if type(text) is str:
      text = text.strip()
      text = unicode(text, 'ISO-8859-1')
    else:
      text = ''
    reVal = ''
    
    if text == '':
      reVal = 'Fine. Be that way!'
    elif text.isupper():
      reVal = 'Woah, chill out!'
    elif text.endswith('?'):
      reVal = 'Sure.'
    else:
      reVal = 'Whatever.'
     
    return reVal
