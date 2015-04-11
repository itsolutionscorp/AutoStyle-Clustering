# yeah sure whatever

def hey(comment):

  if comment.expandtabs().replace(' ','') == '':
    return 'Fine. Be that way!'

  if comment.isupper():
    return 'Whoa, chill out!'

  if comment.endswith('?'):
    return 'Sure.'
  
  # the default case - we haven't returned.
  return 'Whatever.'
