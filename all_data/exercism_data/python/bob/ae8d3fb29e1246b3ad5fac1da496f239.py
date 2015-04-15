class Bob:
  def hey(this, text):
    if not type(text) is str:
      text = ''

    text = text.strip()
    text = unicode(text, 'ISO-8859-1')
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
