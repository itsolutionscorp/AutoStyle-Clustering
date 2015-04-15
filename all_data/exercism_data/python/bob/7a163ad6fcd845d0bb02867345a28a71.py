# Author::  Tim Henigan
# Copyright:: Copyright (c) 2013
# License::   MIT (http://opensource.org/licenses/MIT)

class Bob:
    """Bob is a lackadaisical teenager. In conversation, his responses are
    very limited."""

    def hey(self, request):
        r = Request(request)

        if r.is_empty():
            response = "Fine. Be that way."
        elif r.is_yelled():
            response = "Woah, chill out!"
        elif r.is_question():
            response = "Sure."
        else:
            response = "Whatever."

        return response


class Request:
    """Determine characteristics of a given request"""

    def __init__(self, request):
        if request is None:
            self.request = ""
        else:
            self.request = request.rstrip()

    def is_empty(self):
        return not self.request

    def is_yelled(self):
        return self.request.isupper()

    def is_question(self):
        return self.request.endswith("?")
