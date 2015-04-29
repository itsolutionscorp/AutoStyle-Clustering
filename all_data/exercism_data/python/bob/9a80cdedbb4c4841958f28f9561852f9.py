import string

class Message:
  def __init__(self, statement):
    self.statement = '' if statement is None else str(statement)

  def is_question(self):
    return self.statement[-1] == '?'

  def is_silent(self):
    return string.strip(self.statement) == ''

  def is_shouting(self):
    return string.upper(self.statement) == self.statement

  def is_default(self):
    return True


class Bob:
  def hey(self, statement):
    message = Message(statement)

    for message_type in ['silent', 'shouting', 'question', 'default']:
      query = getattr(message, 'is_%s' % message_type)
      if query():
        response = getattr(self, '%s_response' % message_type)
        return response()

  def question_response(self):
    return 'Sure.'

  def silent_response(self):
    return 'Fine. Be that way!'

  def shouting_response(self):
    return 'Woah, chill out!'

  def default_response(self):
    return 'Whatever.'
