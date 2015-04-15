#!/usr/bin/python

class Bob(object):
    def __init__(self):
        self.question_response = "Sure."
        self.yell_response = "Woah, chill out!"
        self.silent_response = "Fine. Be that way!"
        self.whatever = "Whatever."

    def hey(self, question):
        if not(question) or question.strip()=='':
            return self.silent_response
        if question.isupper():
            return self.yell_response
        elif question.endswith("?"):
            return self.question_response
        return self.whatever
