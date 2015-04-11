import re
import unicodedata

class Bob(object):

  responses = []

  def hey(self, greeting):
    good_responses = (r for r in self.responses if r.responds_to(greeting))
    first_response = next(good_responses, None)

    return first_response.respond(greeting)

class Response(object):

    def __init__(self, greetingExpression, response, preprocess=False):
        self.greetingMatcher = re.compile(greetingExpression, re.UNICODE)
        self.response = response
        self.preprocess = preprocess

    def preprocessed_greeting(self, greeting):
        if self.preprocess:
            greeting = self.preprocess(greeting)
        return greeting

    def responds_to(self, greeting):
        greeting = self.preprocessed_greeting(greeting)
        return True if self.greetingMatcher.match(greeting) else False

    def respond(self, greeting):
        greeting = self.preprocessed_greeting(greeting)
        matches = self.greetingMatcher.match(greeting)
        return self.response % matches.groups()

def normalize_unicode(s):
    if type(s) is not unicode:
        s = unicode(s)
    return unicodedata.normalize("NFKD", s).encode('ASCII', 'ignore')

ignore_non_letters = lambda s: re.sub("[^a-zA-Z]", "", normalize_unicode(s))
all_caps = r"^[A-Z]+$"
Bob.responses.append(Response(all_caps, 'Woah, chill out!', preprocess=ignore_non_letters))
Bob.responses.append(Response(r".*\?$", 'Sure.'))
Bob.responses.append(Response(r"^\s*$", 'Fine. Be that way!'))
Bob.responses.append(Response(r".+", 'Whatever.'))
