class Bob(object):
  def hey(self, question):
    response = 'Fine. Be that way!'

    if question and not question.isspace():
      if question.isupper():
        response = 'Woah, chill out!'
      elif question.endswith('?'):
        response = 'Sure.'
      else:
        response = 'Whatever.'		
 
    return response
