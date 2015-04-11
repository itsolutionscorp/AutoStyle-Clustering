#!/usr/bin/env python3
import re

class bob:
    """Standard Replies"""
    Qu = "Sure."
    Sh = "Whoa, chill out!"
    Si = "Fine. Be that way!"
    Wh = "Whatever."

    """A Few tests"""
    def Question(self, string):
        if string[-1] == "?":
            return True

    def Caps(self, string):
        if string == string.upper():
            return True

    def Words(self, string):
        if re.search(r'[a-zA-Z]+', string) is not None:
            return True

    def Shout(self, string):
        if string[-1] == "!":
            return True

    def Silence(self, string):
        if not string.strip():
            return True

    """Compile it all together and let them have it"""
    def hey(self, string):
        response = self.Wh
        if self.Silence(string):
            response = self.Si
        elif ( self.Words(string) and self.Caps(string) ):
            response = self.Sh
        elif self.Question(string):
            response = self.Qu
        return response
