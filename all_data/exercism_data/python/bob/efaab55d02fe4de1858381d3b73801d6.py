import re

class Bob(object):

  responses = []

  @classmethod
  def says(cls, response):
    if response not in cls.responses:
        cls.responses.append(response) 

  def hey(self, message):
    good_responses = (r for r in self.responses if r.respond_to(message))
    first_response = next(good_responses, None)

    return str(first_response)

class Response(object):

    def __init__(self, response):
        self.response = response

    def to(self, *msg_filters):
        self.msg_filters = msg_filters

        return self

    def respond_to(self, message):
        message_matches = all(f(message) for f in self.msg_filters)
        return message_matches

    def __str__(self):
        return self.response 

Bob.says(Response('Woah, chill out!').to(lambda s: s.isupper()))
Bob.says(Response('Sure.').to(lambda s: s.endswith('?')))
Bob.says(Response('Fine. Be that way!').to(lambda s: re.match(r"^\s*$", s)))
Bob.says(Response('Whatever.').to(lambda s: True))
