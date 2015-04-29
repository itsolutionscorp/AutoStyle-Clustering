# Author::  Tim Henigan
# Copyright:: Copyright (c) 2013
# License::   MIT (http://opensource.org/licenses/MIT)

class Bob:
    """Bob is a lackadaisical teenager. In conversation, his responses are
    very limited."""

    def hey(self, input):
        request = Request(input)

        if request.is_empty():
            response = "Fine. Be that way."
        elif request.is_yelled():
            response = "Woah, chill out!"
        elif request.is_question():
            response = "Sure."
        else:
            response = "Whatever."

        return response


class Request:
    """Determine characteristics of a given request"""

    def __init__(self, input):
        if input is None:
            self.request = ""
        else:
            self.request = input.rstrip()

    def is_empty(self):
        return not self.request

    def is_yelled(self):
        return self.request.isupper()

    def is_question(self):
        return self.request.endswith("?")
