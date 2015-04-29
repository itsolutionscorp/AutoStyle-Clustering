class WhateverResponse:
  @staticmethod
  def answers(message):
    return True

  @staticmethod
  def response():
    return 'Whatever.'

class ShoutResponse:
  @staticmethod
  def answers(message):
    return message == message.upper()

  @staticmethod
  def response():
    return 'Woah, chill out!'

class QuestionResponse:
  @staticmethod
  def answers(message):
    return message.endswith('?')

  @staticmethod
  def response():
    return 'Sure.'

class SilenceResponse:
  @staticmethod
  def answers(message):
    return (message or '').strip() == ''

  @staticmethod
  def response():
    return 'Fine. Be that way!'

class Responder:
  responders = [SilenceResponse, ShoutResponse, QuestionResponse, WhateverResponse]

  def respond_to(self, message):
    return next(r for r in self.responders if r.answers(message)).response()

class Bob:
  def hey(self, message, responder=Responder()):
    return responder.respond_to(message)
