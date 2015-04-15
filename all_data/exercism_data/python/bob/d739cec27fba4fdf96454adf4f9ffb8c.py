import string

class Message:
  def __init__(self, statement):
    self.statement = '' if statement is None else str(statement)

  def question(self):
    return self.statement.endswith('?')

  def silent(self):
    return self.statement.strip() == ''

  def shouting(self):
    return (not self.silent()) and self.statement.upper() == self.statement

  def default(self):
    return True


class Bob:
  def hey(self, statement):
    message = Message(statement)

    responses = [
      (message.silent,    'Fine. Be that way!'),
      (message.shouting,  'Woah, chill out!'),
      (message.question,  'Sure.'),
      (message.default,   'Whatever.')
    ]

    for query, response in responses:
      if query():
        return response
